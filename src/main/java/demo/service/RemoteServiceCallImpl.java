package demo.service;


import demo.exception.InvalidRequestException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteServiceCallImpl implements RemoteServiceCall{

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @Value("${security.oauth2.client.accessTokenUri}")
    private String uri;

    @Value("${security.oauth2.client.callbackUri}")
    private String callbackUri;

    private static final Logger LOG = LoggerFactory.getLogger(RemoteServiceCallImpl.class);

    @Override
    public String authenticate(String code){
        try{
            return getAccessToken(doAuthenticate(code));
        }catch(Exception e){
            LOG.error("Error while authenticate e={}",e.getMessage());
            throw new InvalidRequestException("Login Failure");
        }
    }

    @Override
    public void authorize(String accessToken){
        LOG.info("Getting into authorize");
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("USER"));
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("user", "password", grantedAuths));
    }


    private String getAccessToken(String body){
        JSONObject jsonObject = null;
        LOG.error("response before json parse body={}", body);
        // get the access token from json and request info from Google
        try {
            jsonObject = (JSONObject) new JSONParser().parse(body);
        } catch (ParseException e) {
            LOG.error("Unable to parse json body={}", body);
            throw new RuntimeException("Unable to parse json " + body);
        }
        // google tokens expire after an hour, but since we requested offline access we can get a
        // new token without user involvement via the refresh token
        LOG.info("Json Object={}", jsonObject);
        return (String) jsonObject.get("access_token");
    }

    private String doAuthenticate(String code) throws Exception{
        LOG.info("Getting into authenticate");
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(uri);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("grant_type", "authorization_code"));
        nameValuePairs.add(new BasicNameValuePair("redirect_uri", callbackUri));
        nameValuePairs.add(new BasicNameValuePair("client_id", clientId));
        nameValuePairs.add(new BasicNameValuePair("client_secret", clientSecret));
        nameValuePairs.add(new BasicNameValuePair("code", code));

        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response = httpclient.execute(httppost);
        return EntityUtils.toString(response.getEntity());
    }
}

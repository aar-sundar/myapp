package demo.controller;

import com.google.common.collect.ImmutableMap;
import org.apache.http.client.methods.HttpPost;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.expression.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.RequestEntity.post;


/**
 * Created by 107582 on 11/15/2016.
 */
@Controller
public class CallBackController {

    private static final Logger LOG = LoggerFactory.getLogger(CallBackController.class);

    private static final String  clientId = "781374084326-81kflheo8pensno4c8cuau44o21kdi85.apps.googleusercontent.com";
    private static final String  clientSecret = "D1_heaIzRYJSCHwu4Ubahlki";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value="/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response)
            throws IOException,URISyntaxException, org.json.simple.parser.ParseException{
        Map<String, String[]> paramMap = request.getParameterMap();
        if(paramMap.containsKey("error")){
            LOG.error("Login failed. error={}", paramMap.get("code"));
            response.sendRedirect("/home");
        }else{
            //if(paramMap.containsKey("code")){
                String code = request.getParameter("code");
              LOG.info("Code  = {}", code);
           /* UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl("https://accounts.google.com/o/oauth2/token")
                    .queryParam("code", code)
                    .queryParam("client_id", clientId)
                    .queryParam("client_secret", clientSecret)
                    .queryParam("redirect_uri", "http://localhost:8080/callback")
                    .queryParam("grant_type", "authorization_code");
                String uri = builder.build().toString();*/
            Map<String, Object> entityRequest = new HashMap<>();
            entityRequest.put("code", code);
            entityRequest.put("client_id", clientId);
            //entityRequest.put("scope", "openid%20email");
            entityRequest.put("redirect_uri", "http://localhost:8080/callback");
            entityRequest.put("client_secret", clientSecret);
            entityRequest.put("grant_type", "authorization_code");
           // String uri = "https://accounts.google.com/o/oauth2/token";
            //String uri = "https://www.googleapis.com/oauth2/v4/token";
            String uri = "https://www.googleapis.com/oauth2/v4/token";

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            //String body  = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(entityRequest), String.class).getBody();
            String body = restTemplate.postForObject(uri,new HttpEntity<>(entityRequest, headers), String.class);


            JSONObject jsonObject = null;

            // get the access token from json and request info from Google
            try {
                jsonObject = (JSONObject) new JSONParser().parse(body);
            } catch (ParseException e) {
                throw new RuntimeException("Unable to parse json " + body);
            }

            // google tokens expire after an hour, but since we requested offline access we can get a new token without user involvement via the refresh token
            String accessToken = (String) jsonObject.get("access_token");

            // you may want to store the access token in session
            request.getSession().setAttribute("access_token", accessToken);
                LOG.info("Login Success. code={}", paramMap.get("code"));
           // }
            response.sendRedirect("/dashboard");
        }
    }
}

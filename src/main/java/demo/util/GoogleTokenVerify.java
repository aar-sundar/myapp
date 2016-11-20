package demo.util;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;
import com.google.gson.Gson;
import demo.model.security.GoogleToken;
import demo.model.security.TokenStatus;
import demo.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import demo.model.security.Checker;

import java.io.IOException;

@Component
public class GoogleTokenVerify {

    private static final Logger LOG = LoggerFactory.getLogger(GoogleTokenVerify.class);

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    /**
     * Default HTTP transport to use to make HTTP requests.
     */
    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    /**
     * Default JSON factory to use to deserialize JSON.
     */
    private static final JacksonFactory JSON_FACTORY = new JacksonFactory();
    /**
     * Gson object to serialize JSON responses to requests to this servlet.
     */
    private static final Gson GSON = new Gson();

    public User verifyToken(GoogleToken token){
        LOG.info("Started verifying Token");
        TokenStatus idStatus = new TokenStatus();

        User googleUser = new User();

        if(token.getIdToken()!= null){
            Checker checker = new Checker(new String[]{clientId}, clientId);
            GoogleIdToken.Payload jwt = checker.check(token.getIdToken());

            if (jwt == null) {
                // This is not a valid token.
                idStatus.setValid(false);
                idStatus.setId("");
                idStatus.setMessage("Invalid ID Token.");
            } else {
                idStatus.setValid(true);
                String gplusId = (String)jwt.get("sub");
                idStatus.setId(gplusId);
                idStatus.setMessage("ID Token is valid.");
            }
        } else {
            idStatus.setMessage("ID Token not provided");
        }

        TokenStatus accessStatus = new TokenStatus();
        if (token.getAccessToken() != null) {
            // Check that the Access Token is valid.
            try {
                GoogleCredential credential = new GoogleCredential().setAccessToken(token.getAccessToken());
                Oauth2 oauth2 = new Oauth2.Builder(
                        TRANSPORT, JSON_FACTORY, credential).build();
                Tokeninfo tokenInfo = oauth2.tokeninfo()
                        .setAccessToken(token.getAccessToken()).execute();
                if (!tokenInfo.getIssuedTo().equals(clientId)) {
                    // This is not meant for this app. It is VERY important to check
                    // the client ID in order to prevent man-in-the-middle attacks.
                    accessStatus.setValid(false);
                    accessStatus.setId("");
                    accessStatus.setMessage("Access Token not meant for this app.");
                } else {
                    googleUser.setEmail(tokenInfo.getEmail());
                    accessStatus.setValid(true);
                    accessStatus.setId(tokenInfo.getUserId());
                    accessStatus.setMessage("Access Token is valid.");
                }
            } catch (IOException e) {
                accessStatus.setValid(false);
                accessStatus.setId("");
                accessStatus.setMessage("Invalid Access Token.");
            }
        } else {
            accessStatus.setMessage("Access Token not provided");
        }
        return googleUser;
    }

}



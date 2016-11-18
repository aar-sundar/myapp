package demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

   @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.callbackUri}")
    private String callbackUri;

    @Value("${security.oauth2.client.userAuthorizationUri}")
    private String authUri;

    @Value("${security.oauth2.client.responseType}")
    private String responseType;

    @Value("${security.oauth2.client.scope}")
    private String scope;

    @Value("${security.oauth2.client.accessType}")
    private String accessType;

    @Value("${security.oauth2.client.approvalPrompt}")
    private String approvalPrompt;

    @Value("${security.oauth2.client.state}")
    private String state;

    @RequestMapping(value="/login")
    public void login(HttpServletResponse response) throws IOException{
        LOG.info("Getting into login to validate");

        StringBuilder oauthUrl = new StringBuilder().append(authUri)
                .append("?client_id=").append(clientId) // the client id from the api console registration
                .append("&response_type=").append(responseType)
                .append("&scope=").append(scope) // scope is the api permissions we are requesting
                .append("&redirect_uri=").append(callbackUri) // the servlet that google redirects to after authorization
                .append("&state=").append(state)
                .append("&access_type=").append(accessType) // here we are asking to access to user's data while they are not signed in
                .append("&approval_prompt=").append(approvalPrompt); // this requires them to verify which account to use, if they are already signed in

        LOG.info("redirect oauthUrl={}",oauthUrl);

        response.sendRedirect(oauthUrl.toString());
    }
}

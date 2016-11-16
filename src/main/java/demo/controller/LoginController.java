package demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 107582 on 11/15/2016.
 */
@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    private static final String  clientId = "781374084326-81kflheo8pensno4c8cuau44o21kdi85.apps.googleusercontent.com";
    @RequestMapping(value="/login")
    public void login(HttpServletResponse response) throws IOException{
        LOG.info("Getting into login to validate");
        StringBuilder oauthUrl = new StringBuilder().append("https://accounts.google.com/o/oauth2/auth")
                .append("?client_id=").append(clientId) // the client id from the api console registration
                .append("&response_type=code")
                .append("&scope=openid%20email") // scope is the api permissions we are requesting
                .append("&redirect_uri=http://localhost:8080/callback") // the servlet that google redirects to after authorization
                .append("&state=this_can_be_anything_to_help_correlate_the_response%3Dlike_session_id")
                .append("&access_type=offline") // here we are asking to access to user's data while they are not signed in
                .append("&approval_prompt=force"); // this requires them to verify which account to use, if they are already signed in
        LOG.info("redirect oauthUrl={}",oauthUrl);
        response.sendRedirect(oauthUrl.toString());
        //return new ModelAndView("redirect:/"+oauthUrl);
    }
}

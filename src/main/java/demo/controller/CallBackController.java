package demo.controller;

import demo.model.security.GoogleToken;
import demo.service.AuthorizationService;
import demo.service.RemoteServiceCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;


@Controller
public class CallBackController {

    private static final Logger LOG = LoggerFactory.getLogger(CallBackController.class);

    private RemoteServiceCall remoteServiceCall;
    private AuthorizationService authorizationService;

    @Autowired
    public CallBackController(RemoteServiceCall remoteServiceCall,
                              AuthorizationService authorizationService){
        this.remoteServiceCall = remoteServiceCall;
        this.authorizationService = authorizationService;
    }

    @RequestMapping(value="/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response)
            throws IOException,URISyntaxException, org.json.simple.parser.ParseException{
        Map<String, String[]> paramMap = request.getParameterMap();
        if(paramMap.containsKey("error")){
            LOG.error("Login failed. error={}", paramMap.get("error"));
            response.sendRedirect("/home");
        }else{
            String code = request.getParameter("code");
            LOG.info("Code  = {}", code);
            GoogleToken token = remoteServiceCall.authenticate(code);
            authorizationService.authorize(token);
            LOG.info("Login Success. token={}", token.toString());
            response.sendRedirect("/dashboard");
        }
    }

}

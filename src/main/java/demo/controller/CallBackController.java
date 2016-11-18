package demo.controller;

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

    @Autowired
    public CallBackController(RemoteServiceCall remoteServiceCall){
        this.remoteServiceCall = remoteServiceCall;
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
            String accessToken = remoteServiceCall.authenticate(code);
            remoteServiceCall.authorize(accessToken);
            LOG.info("Login Success. token={}", accessToken);
            response.sendRedirect("/dashboard");
        }
    }

}

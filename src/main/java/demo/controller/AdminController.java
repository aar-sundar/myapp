package demo.controller;

import demo.service.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    private AuthorizationService authorizationService;

    @Autowired
    public AdminController(AuthorizationService authorizationService){
        this.authorizationService = authorizationService;
    }

    @RequestMapping("/admin")
    public String adminAccess(@RequestParam(value= "email", defaultValue = "brooksdale.pammal@gmail.com",
            required=false) String email){
        LOG.info("Default Admin Access");
        authorizationService.adminAccess(email);
        return "/admin";
    }
}

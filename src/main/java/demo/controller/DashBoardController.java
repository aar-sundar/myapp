package demo.controller;

import demo.config.SecurityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DashBoardController {
    private static final Logger LOG = LoggerFactory.getLogger(DashBoardController.class);

    @Autowired
    SecurityHelper securityHelper;

    @RequestMapping("/dashboard")
    public ModelAndView dashboard(){
        String user =securityHelper.getCurrentUser().getEmail();
        LOG.info("Welcome , you are are authorised user={}", user);
        return new ModelAndView("/dashboard");
    }
}

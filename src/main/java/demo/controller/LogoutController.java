package demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LogoutController {

    private static final Logger LOG = LoggerFactory.getLogger(LogoutController.class);

    @RequestMapping("/logout")
    public void logout(HttpServletResponse response) throws IOException{
        LOG.info("Logging out the user manually");
        SecurityContextHolder.clearContext();
        response.sendRedirect("/home");
    }
}

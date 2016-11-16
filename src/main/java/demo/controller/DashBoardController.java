package demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 107582 on 11/15/2016.
 */
@Controller
public class DashBoardController {
    private static final Logger LOG = LoggerFactory.getLogger(DashBoardController.class);

    @RequestMapping("/dashboard")
    public String dashboard(){
        LOG.info("Welcome , you are are authorised");
        return "/dashboard";
    }
}

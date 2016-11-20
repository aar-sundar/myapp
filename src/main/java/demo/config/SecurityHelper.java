package demo.config;

import demo.model.user.User;
import demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityHelper {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityHelper.class);

    private UserService userService;

    @Autowired
    public SecurityHelper(UserService userService){
        this.userService = userService;
    }

    public User getCurrentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserByEmail(email);
    }
}

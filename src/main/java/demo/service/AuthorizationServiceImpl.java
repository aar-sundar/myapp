package demo.service;

import demo.exception.InvalidRequestException;
import demo.model.security.GoogleToken;
import demo.util.GoogleTokenVerify;
import demo.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import demo.model.user.User;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

    private GoogleTokenVerify googleTokenVerify;
    private UserService userService;

    @Autowired
    public AuthorizationServiceImpl(GoogleTokenVerify googleTokenVerify, UserService userService){
        this.googleTokenVerify = googleTokenVerify;
        this.userService = userService;
    }

    public void authorize(GoogleToken token){
            LOG.info("Getting into authorize");
            User user = googleTokenVerify.verifyToken(token);
            if(user != null && Utility.validate(user.getEmail())){
                // check whether is authorised user
                if(userService.getUserByEmail(user.getEmail()) == null){
                    throw new InvalidRequestException("User don't have access");
                } else{
                    grantAccess(user);
                }

            }else{
                throw new InvalidRequestException("Invalid User");
            }


    }

    @Override
    public void adminAccess(String email){
        if(Utility.validate(email) && email.equalsIgnoreCase("brooksdale.pammal@gmail.com")){
            User adminUser = new User();
            grantAccess(adminUser);
        }else{
            throw new InvalidRequestException("Invalid User");
        }
    }

    private void grantAccess(User user){
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("USER"));
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user.getEmail(), "", grantedAuths));
    }
}

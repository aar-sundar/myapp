package demo.service;

import demo.exception.AlreadyFoundException;
import demo.exception.InvalidRequestException;
import demo.exception.NotFoundException;
import demo.model.user.User;
import demo.repository.UserRepository;
import demo.request.UserCreateRequest;
import demo.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(User user) {
        String email = user.getEmail();
        User userFound = findByEmail(email);
        if(userFound != null){
            throw new AlreadyFoundException("User with the email already available");
        }
        User userCreated = userRepository.save(user);
        LOG.info("User created successfully = {}", userCreated);
        return userCreated.getId();
    }

    @Override
    public User getUserById(String id) {
        User user = userRepository.findOne(id);
        if(user == null){
            LOG.error("User Information not found for the Id ={}", id);
            throw new NotFoundException("User Information not found for the Id:"+ id);
        }
        LOG.info("User found in db = {}", user);
        return  user;
    }

    @Override
    public User getUserByEmail(String email){
        User user = findByEmail(email);
        if(user == null){
            LOG.error("User Information not found for the email ={}", email);
            throw new NotFoundException("User Information not found for the email : "+ email);
        } else {
            return user;
        }
    }

    private User findByEmail(String email){
        User user = null;
        if(validateEmail(email)){
            user = userRepository.findByEmail(email);
        }
        return user;
    }

    private boolean validateEmail(String email){
        if(!Utility.validate(email)){
            LOG.error("Invalid email = {}", email);
            throw new InvalidRequestException("invalid email");
        }
        return true;
    }
}

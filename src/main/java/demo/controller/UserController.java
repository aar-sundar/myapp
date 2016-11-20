package demo.controller;

import demo.model.user.User;
import demo.request.UserCreateRequest;
import demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @ApiOperation(value = "", notes = "Creates an User record in DB")
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user){
        LOG.info("Calling createUser");
        return new ResponseEntity<> (userService.createUser(user), HttpStatus.CREATED);
    }

    @ApiOperation(value = "", notes = "Get an User record in DB for a given Id")
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable String id){
        LOG.info("Getting User for the id={}", id);
        return userService.getUserById(id);
    }

    @ApiOperation(value = "", notes = "Get an User record in DB for a given email")
    @RequestMapping(value="/get", method = RequestMethod.GET)
    public User getUserByEmail(@RequestParam String email){
        LOG.info("Getting User for the email={}", email);
        return userService.getUserByEmail(email);
    }
}

package demo.service;

import demo.model.user.User;
import demo.request.UserCreateRequest;


public interface UserService {

    String createUser(User user);

    User getUserById(String id);

    User getUserByEmail(String email);
}

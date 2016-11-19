package demo.service;

import demo.model.User;
import demo.request.UserCreateRequest;


public interface UserService {

    String createEmployee(UserCreateRequest request);

    User getUserById(String id);

    User getUserByEmail(String email);
}

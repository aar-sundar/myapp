package demo.service;


import demo.model.security.GoogleToken;

public interface AuthorizationService {

    void authorize(GoogleToken token);

    void adminAccess(String email);
}

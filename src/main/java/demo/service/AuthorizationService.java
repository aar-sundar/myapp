package demo.service;


import demo.model.GoogleToken;

public interface AuthorizationService {

    void authorize(GoogleToken token);

    void adminAccess(String email);
}

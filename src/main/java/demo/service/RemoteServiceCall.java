package demo.service;


import demo.model.security.GoogleToken;

public interface RemoteServiceCall {

    GoogleToken authenticate(String code);

}

package demo.service;


import demo.model.GoogleToken;

public interface RemoteServiceCall {

    GoogleToken authenticate(String code);

}

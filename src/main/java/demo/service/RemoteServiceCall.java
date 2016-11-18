package demo.service;


public interface RemoteServiceCall {

    String authenticate(String code);

    void authorize(String token);
}

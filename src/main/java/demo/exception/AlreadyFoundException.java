package demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyFoundException extends RuntimeException{

    public AlreadyFoundException(String message){
        super(message);
    }
}

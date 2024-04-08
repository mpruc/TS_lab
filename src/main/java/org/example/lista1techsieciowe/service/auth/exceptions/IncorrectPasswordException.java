package org.example.lista1techsieciowe.service.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IncorrectPasswordException extends RuntimeException {
    private IncorrectPasswordException  (String message){
        super(message);
    }
    public static ResponseStatusException create() {
        IncorrectPasswordException exception = new IncorrectPasswordException(String.format("Entererd password is incorrect."));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}

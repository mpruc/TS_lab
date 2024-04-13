package org.example.lista1techsieciowe.service.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends RuntimeException{
    private UserNotFoundException(String message) {
        super(message);
    }
    public static ResponseStatusException create (Integer id) {
        UserNotFoundException exception = new UserNotFoundException(String.format("User with id %s not found", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}

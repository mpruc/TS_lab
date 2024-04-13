package org.example.lista1techsieciowe.service.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends RuntimeException {

    private UserAlreadyExistsException(String message) {
        super(message);
    }
    public static ResponseStatusException create (String username) {
        UserAlreadyExistsException exception = new UserAlreadyExistsException(String.format("Username %s is already taken", username));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}

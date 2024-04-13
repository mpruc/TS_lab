package org.example.lista1techsieciowe.service.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserWithGivenLoginDoesntExistException extends RuntimeException {
    private UserWithGivenLoginDoesntExistException(String message) {
        super(message);
    }
    public static ResponseStatusException create(String username) {
        UserWithGivenLoginDoesntExistException exception = new UserWithGivenLoginDoesntExistException(String.format(("There is no user with username %s "), username));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}


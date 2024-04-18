package org.example.lista1techsieciowe.service.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception indicating that a user with the specified username already exists.
 */
public class UserAlreadyExistsException extends RuntimeException {

    private UserAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Creates a ResponseStatusException with HttpStatus.CONFLICT indicating that the username is already taken.
     * @param username The username that is already taken.
     * @return The ResponseStatusException instance.
     */
    public static ResponseStatusException create(String username) {
        UserAlreadyExistsException exception = new UserAlreadyExistsException(String.format("Username %s is already taken", username));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}

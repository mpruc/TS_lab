package org.example.lista1techsieciowe.service.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception indicating that a user with the specified ID was not found.
 */
public class UserNotFoundException extends RuntimeException {

    private UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates a ResponseStatusException with HttpStatus.NOT_FOUND indicating that the user was not found.
     *
     * @param id The ID of the user that was not found.
     * @return The ResponseStatusException instance.
     */
    public static ResponseStatusException create(Integer id) {
        UserNotFoundException exception = new UserNotFoundException(String.format("User with id %s not found", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}

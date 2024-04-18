package org.example.lista1techsieciowe.service.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception indicating that there is no user with the specified username.
 */
public class UserWithGivenLoginDoesntExistException extends RuntimeException {

    private UserWithGivenLoginDoesntExistException(String message) {
        super(message);
    }

    /**
     * Creates a ResponseStatusException with HttpStatus.NOT_FOUND indicating that no user with the given username exists.
     * @param username The username for which no user was found.
     * @return The ResponseStatusException instance.
     */
    public static ResponseStatusException create(String username) {
        UserWithGivenLoginDoesntExistException exception = new UserWithGivenLoginDoesntExistException(String.format("There is no user with username %s", username));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}

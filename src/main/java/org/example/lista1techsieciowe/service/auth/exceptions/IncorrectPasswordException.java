package org.example.lista1techsieciowe.service.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception indicating that the provided password is incorrect.
 */
public class IncorrectPasswordException extends RuntimeException {

    private IncorrectPasswordException(String message) {
        super(message);
    }

    /**
     * Creates a ResponseStatusException with HttpStatus.CONFLICT indicating the incorrect password.
     * @return The ResponseStatusException instance.
     */
    public static ResponseStatusException create() {
        IncorrectPasswordException exception = new IncorrectPasswordException("Entered password is incorrect.");
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}

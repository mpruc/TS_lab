package org.example.lista1techsieciowe.service.auth.exceptions;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IncorrectRoleException extends RuntimeException {
    private IncorrectRoleException(String message) {
        super(message);
    }
    public static ResponseStatusException create () {
        IncorrectRoleException exception  = new IncorrectRoleException(String.format("Registered user's role must be 'LIBRARIAN' OR 'READER'"));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}

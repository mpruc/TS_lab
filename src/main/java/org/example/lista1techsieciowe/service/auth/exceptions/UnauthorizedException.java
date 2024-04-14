package org.example.lista1techsieciowe.service.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedException extends RuntimeException {
    private UnauthorizedException (String message) {
        super(message);
    }
    public static ResponseStatusException create() {
        UnauthorizedException exception = new UnauthorizedException("You are not authorized to perform this action");
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}

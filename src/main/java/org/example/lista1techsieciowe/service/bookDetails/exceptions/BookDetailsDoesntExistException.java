package org.example.lista1techsieciowe.service.bookDetails.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookDetailsDoesntExistException extends RuntimeException {
    private BookDetailsDoesntExistException (String message) {
        super(message);
    }
    public static ResponseStatusException create (Integer id) {
        BookDetailsDoesntExistException exception = new BookDetailsDoesntExistException(String.format("Book details with id '%s' does not exist", id));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}


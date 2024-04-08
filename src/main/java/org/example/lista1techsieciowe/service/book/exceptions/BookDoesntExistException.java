package org.example.lista1techsieciowe.service.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookDoesntExistException extends RuntimeException {
    private BookDoesntExistException (String message) {
        super(message);
    }
    public static ResponseStatusException create(Integer id) {
        BookDoesntExistException exception = new BookDoesntExistException(String.format("Book with id '%s' does not exist", id));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}



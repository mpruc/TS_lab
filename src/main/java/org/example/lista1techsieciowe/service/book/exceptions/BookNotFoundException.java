package org.example.lista1techsieciowe.service.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookNotFoundException extends RuntimeException{
    private BookNotFoundException(String message) {
        super(message);
    }
    public static ResponseStatusException create(String title) {
        BookNotFoundException exception = new BookNotFoundException(String.format("There is no book titled '%s'", title));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}

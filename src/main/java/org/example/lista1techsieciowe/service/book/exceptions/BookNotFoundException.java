package org.example.lista1techsieciowe.service.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception indicating that a book with the specified title was not found.
 */
public class BookNotFoundException extends RuntimeException {

    private BookNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates a ResponseStatusException with HttpStatus.NOT_FOUND indicating that the book was not found.
     * @param title The title of the book that was not found.
     * @return The ResponseStatusException instance.
     */
    public static ResponseStatusException create(String title) {
        BookNotFoundException exception = new BookNotFoundException(String.format("There is no book titled '%s'", title));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}

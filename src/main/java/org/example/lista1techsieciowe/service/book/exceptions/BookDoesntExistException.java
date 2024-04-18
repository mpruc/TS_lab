package org.example.lista1techsieciowe.service.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception indicating that a book with the specified ID does not exist.
 */
public class BookDoesntExistException extends RuntimeException {

    private BookDoesntExistException(String message) {
        super(message);
    }

    /**
     * Creates a ResponseStatusException with HttpStatus.NOT_FOUND indicating that the book does not exist.
     * @param id The ID of the book that does not exist.
     * @return The ResponseStatusException instance.
     */
    public static ResponseStatusException create(Integer id) {
        BookDoesntExistException exception = new BookDoesntExistException(String.format("Book with id '%s' does not exist", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}

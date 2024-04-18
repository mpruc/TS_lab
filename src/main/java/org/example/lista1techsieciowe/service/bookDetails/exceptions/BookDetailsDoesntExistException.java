package org.example.lista1techsieciowe.service.bookDetails.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception indicating that book details with the specified ID do not exist.
 */
public class BookDetailsDoesntExistException extends RuntimeException {

    private BookDetailsDoesntExistException(String message) {
        super(message);
    }

    /**
     * Creates a ResponseStatusException with HttpStatus.NOT_FOUND indicating that the book details do not exist.
     * @param id The ID of the book details that do not exist.
     * @return The ResponseStatusException instance.
     */
    public static ResponseStatusException create(Integer id) {
        BookDetailsDoesntExistException exception = new BookDetailsDoesntExistException(String.format("Book details with id '%s' does not exist", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}

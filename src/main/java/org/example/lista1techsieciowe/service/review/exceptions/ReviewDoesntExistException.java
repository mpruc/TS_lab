package org.example.lista1techsieciowe.service.review.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception indicating that a review with the specified ID does not exist.
 */
public class ReviewDoesntExistException extends RuntimeException {

    private ReviewDoesntExistException(String message) {
        super(message);
    }

    /**
     * Creates a ResponseStatusException with HttpStatus.NOT_FOUND indicating that the review does not exist.
     * @param id The ID of the review that does not exist.
     * @return The ResponseStatusException instance.
     */
    public static ResponseStatusException create(Integer id) {
        ReviewDoesntExistException exception = new ReviewDoesntExistException(String.format("Review with id %s does not exist", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}

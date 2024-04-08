package org.example.lista1techsieciowe.service.review.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReviewDoesntExistException extends RuntimeException {
    private ReviewDoesntExistException (String message) {
        super(message);
    }
    public static ResponseStatusException create(Integer id) {
        ReviewDoesntExistException exception = new ReviewDoesntExistException(String.format("Review with id %s does not exist", id));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}


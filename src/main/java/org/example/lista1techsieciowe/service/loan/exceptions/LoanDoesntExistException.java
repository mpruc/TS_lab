package org.example.lista1techsieciowe.service.loan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception indicating that a loan with the specified ID does not exist.
 */
public class LoanDoesntExistException extends RuntimeException {

    private LoanDoesntExistException(String message) {
        super(message);
    }

    /**
     * Creates a ResponseStatusException with HttpStatus.NOT_FOUND indicating that the loan does not exist.
     * @param id The ID of the loan that does not exist.
     * @return The ResponseStatusException instance.
     */
    public static ResponseStatusException create(Integer id) {
        LoanDoesntExistException exception = new LoanDoesntExistException(String.format("Loan with id %s does not exist", id));
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }
}

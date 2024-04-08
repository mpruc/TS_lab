package org.example.lista1techsieciowe.service.loan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanDoesntExistException extends RuntimeException {
    private LoanDoesntExistException (String message) {
        super(message);
    }
    public static ResponseStatusException create(Integer id) {
        LoanDoesntExistException exception = new LoanDoesntExistException(String.format("Loan with id %s does not exist", id));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }
}
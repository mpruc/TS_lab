package org.example.lista1techsieciowe.controller.dto.loan;

import org.example.lista1techsieciowe.controller.dto.book.GetBookDto;
import org.example.lista1techsieciowe.controller.dto.user.GetUserDto;
import java.sql.Date;

/**
 * Data Transfer Object (DTO) representing the response for retrieving a loan.
 */
public class GetLoanResponseDto {

    private Integer loanId;
    private GetBookDto bookId;
    private GetUserDto userId;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;

    /**
     * Constructs a new GetLoanResponseDto.
     *
     * @param loanId The ID of the loan.
     * @param bookId The book associated with the loan.
     * @param userId The user associated with the loan.
     * @param loanDate The date the loan was initiated.
     * @param dueDate The due date for returning the book.
     * @param returnDate The date the book was returned (optional).
     */
    public GetLoanResponseDto(Integer loanId, GetBookDto bookId, GetUserDto userId, Date loanDate, Date dueDate, Date returnDate) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    /**
     * Retrieves the ID of the loan.
     * @return The loan ID.
     */
    public Integer getLoanId() {
        return loanId;
    }

    /**
     * Sets the ID of the loan.
     * @param loanId The loan ID.
     */
    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    /**
     * Retrieves the book associated with the loan.
     * @return The book associated with the loan.
     */
    public GetBookDto getBookId() {
        return bookId;
    }

    /**
     * Sets the book associated with the loan.
     * @param bookId The book associated with the loan.
     */
    public void setBookId(GetBookDto bookId) {
        this.bookId = bookId;
    }

    /**
     * Retrieves the user associated with the loan.
     * @return The user associated with the loan.
     */
    public GetUserDto getUser() {
        return userId;
    }

    /**
     * Sets the user associated with the loan.
     * @param user The user associated with the loan.
     */
    public void setUser(GetUserDto user) {
        this.userId = user;
    }

    /**
     * Retrieves the date the loan was initiated.
     * @return The loan date.
     */
    public Date getLoanDate() {
        return loanDate;
    }

    /**
     * Sets the date the loan was initiated.
     * @param loanDate The loan date.
     */
    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    /**
     * Retrieves the due date for returning the book.
     * @return The due date.
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date for returning the book.
     * @param dueDate The due date.
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Retrieves the date the book was returned (if available).
     * @return The return date, or null if the book has not been returned yet.
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the date the book was returned.
     * @param returnDate The return date.
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}

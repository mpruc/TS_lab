package org.example.lista1techsieciowe.controller.dto.loan;

import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Date;

/**
 * Data Transfer Object (DTO) representing the response for creating a loan.
 */
public class CreateLoanResponseDto {

    @Schema(name = "loanId", example ="1" )
    private Integer loanId;

    @Schema(name = "book", example ="1" )
    private Integer book;

    @Schema(name = "user", example ="1" )
    private Integer user;

    private Date loanDate;
    private Date dueDate;
    private Date returnDate;

    /**
     * Constructs a new CreateLoanResponseDto with the specified parameters.
     *
     * @param loanId The ID of the created loan.
     * @param book The ID of the book being loaned.
     * @param user The ID of the user borrowing the book.
     * @param loanDate The date the loan was initiated.
     * @param dueDate The due date for returning the book.
     * @param returnDate The date the book was returned (optional).
     */
    public CreateLoanResponseDto(Integer loanId, Integer book, Integer user, Date loanDate, Date dueDate, Date returnDate) {
        this.loanId = loanId;
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    /**
     * Retrieves the ID of the created loan.
     * @return The ID of the loan.
     */
    public Integer getLoanId() {
        return loanId;
    }

    /**
     * Sets the ID of the created loan.
     * @param loanId The ID of the loan.
     */
    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    /**
     * Retrieves the ID of the book being loaned.
     * @return The ID of the book.
     */
    public Integer getBook() {
        return book;
    }

    /**
     * Sets the ID of the book being loaned.
     * @param book The ID of the book.
     */
    public void setBook(Integer book) {
        this.book = book;
    }

    /**
     * Retrieves the ID of the user borrowing the book.
     * @return The ID of the user.
     */
    public Integer getUser() {
        return user;
    }

    /**
     * Sets the ID of the user borrowing the book.
     * @param user The ID of the user.
     */
    public void setUser(Integer user) {
        this.user = user;
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

package org.example.lista1techsieciowe.controller.dto.loan;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Data Transfer Object (DTO) representing the creation of a loan.
 */
public class CreateLoanDto {

    @NotNull
    @Schema(name = "book", example ="1" )
    private Integer book;

    @Schema(name = "user", example ="1" )
    @NotNull
    private Integer user;

    @NotNull
    @Schema(name = "loan date", example ="2024-01-01" )
    private Date loanDate;

    @NotNull
    @Schema(name = "due date", example ="2024-02-01" )
    private Date dueDate;

    private Date returnDate;

    /**
     * Constructs a new CreateLoanDto with the specified parameters.
     *
     * @param book The ID of the book being loaned.
     * @param user The ID of the user borrowing the book.
     * @param loanDate The date the loan was initiated.
     * @param dueDate The due date for returning the book.
     * @param returnDate The date the book was returned (optional).
     */
    public CreateLoanDto(Integer book, Integer user, Date loanDate, Date dueDate, Date returnDate) {
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
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

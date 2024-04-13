package org.example.lista1techsieciowe.controller.dto.loan;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class CreateLoanDto {
    @NotNull
    private Integer book;
    @NotNull
    private Integer user;
    @NotNull
    private Date loanDate;
    @NotNull
    private Date dueDate;
    private Date returnDate;

    public CreateLoanDto(Integer book, Integer user, Date loanDate, Date dueDate, Date returnDate) {
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
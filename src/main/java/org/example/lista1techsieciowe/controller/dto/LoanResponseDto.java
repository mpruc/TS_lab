package org.example.lista1techsieciowe.controller.dto;

import java.sql.Date;

public class LoanResponseDto {
    private Integer loanId;
    private Integer book;
    private Integer user;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;

    public LoanResponseDto(Integer loanId, Integer book, Integer user, Date loanDate, Date dueDate, Date returnDate) {
        this.loanId = loanId;
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
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



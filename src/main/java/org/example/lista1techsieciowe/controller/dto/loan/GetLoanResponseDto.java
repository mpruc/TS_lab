package org.example.lista1techsieciowe.controller.dto.loan;

import org.example.lista1techsieciowe.controller.dto.book.GetBookDto;
import org.example.lista1techsieciowe.controller.dto.user.GetUserDto;

import java.sql.Date;

public class GetLoanResponseDto {
    private Integer loanId;
    private GetBookDto bookId;
    private GetUserDto userId;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;

    public GetLoanResponseDto(Integer loanId, GetBookDto bookId, GetUserDto userId, Date loanDate, Date dueDate, Date returnDate) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.userId = userId;
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

    public GetBookDto getBookId() {
        return bookId;
    }

    public void setBookId(GetBookDto bookId) {
        this.bookId = bookId;
    }

    public GetUserDto getUser() {
        return userId;
    }

    public void setUser(GetUserDto user) {
        this.userId = user;
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

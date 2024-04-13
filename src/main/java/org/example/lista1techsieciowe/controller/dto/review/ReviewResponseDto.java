package org.example.lista1techsieciowe.controller.dto.review;

import org.example.lista1techsieciowe.controller.dto.book.GetBookDto;
import org.example.lista1techsieciowe.controller.dto.user.GetUserDto;
import org.example.lista1techsieciowe.entity.Book;
import org.example.lista1techsieciowe.entity.User;

import java.sql.Date;

public class ReviewResponseDto {
    private Integer reviewId;
    private GetBookDto book;
    private GetUserDto user;
    private Double grade;
    private String comment;
    private Date reviewDate;

    public ReviewResponseDto(Integer reviewId, GetBookDto book, GetUserDto user, Double grade, String comment, Date reviewDate) {
        this.reviewId = reviewId;
        this.book = book;
        this.user = user;
        this.grade = grade;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public GetBookDto getBook() {
        return book;
    }

    public void setBook(GetBookDto book) {
        this.book = book;
    }

    public GetUserDto getUser() {
        return user;
    }

    public void setUser(GetUserDto user) {
        this.user = user;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }



    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}

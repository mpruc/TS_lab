package org.example.lista1techsieciowe.controller.dto;

import org.example.lista1techsieciowe.entity.Book;
import org.example.lista1techsieciowe.entity.User;

import java.sql.Date;

public class ReviewResponseDto {
    private Integer reviewId;
    private Book book;
    private User user;
    private Double grade;
    private String comment;
    private Date reviewDate;

    public ReviewResponseDto(Integer reviewId, Book book, User user, Double grade, String comment, Date reviewDate) {
        this.reviewId = reviewId;
        this.book = book;
        this.user = user;
        this.grade = grade;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

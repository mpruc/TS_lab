package org.example.lista1techsieciowe.controller.dto;

import java.sql.Date;

public class ReviewDto {
    private Integer book;
    private Integer user;
    private Integer grade;
    private String comment;
    private Date reviewDate;

    public ReviewDto(Integer book, Integer user, Integer grade, String comment, Date reviewDate) {
        this.book = book;
        this.user = user;
        this.grade = grade;
        this.comment = comment;
        this.reviewDate = reviewDate;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
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

package org.example.lista1techsieciowe.controller.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Date;

public class ReviewDto {
    @Schema(name = "book", example ="1" )
    private Integer book;
    @Schema(name = "user", example ="1" )
    private Integer user;
    @Schema(name = "grade", example ="4" )
    private Integer grade;
    @Schema(name = "comment", example ="great" )
    private String comment;
    @Schema(name = "review date", example ="2024-01-01" )
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

package org.example.lista1techsieciowe.controller.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Date;

/**
 * Data Transfer Object (DTO) representing a review.
 */
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

    /**
     * Constructs a new ReviewDto with the specified parameters.
     *
     * @param book The ID of the book being reviewed.
     * @param user The ID of the user who submitted the review.
     * @param grade The grade given to the book.
     * @param comment The comment associated with the review.
     * @param reviewDate The date the review was submitted.
     */
    public ReviewDto(Integer book, Integer user, Integer grade, String comment, Date reviewDate) {
        this.book = book;
        this.user = user;
        this.grade = grade;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    /**
     * Retrieves the ID of the book being reviewed.
     * @return The book ID.
     */
    public Integer getBook() {
        return book;
    }

    /**
     * Sets the ID of the book being reviewed.
     * @param book The book ID.
     */
    public void setBook(Integer book) {
        this.book = book;
    }

    /**
     * Retrieves the ID of the user who submitted the review.
     * @return The user ID.
     */
    public Integer getUser() {
        return user;
    }

    /**
     * Sets the ID of the user who submitted the review.
     * @param user The user ID.
     */
    public void setUser(Integer user) {
        this.user = user;
    }

    /**
     * Retrieves the grade given to the book.
     * @return The grade.
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * Sets the grade given to the book.
     * @param grade The grade.
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * Retrieves the comment associated with the review.
     * @return The comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment associated with the review.
     * @param comment The comment.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Retrieves the date the review was submitted.
     * @return The review date.
     */
    public Date getReviewDate() {
        return reviewDate;
    }

    /**
     * Sets the date the review was submitted.
     * @param reviewDate The review date.
     */
    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}

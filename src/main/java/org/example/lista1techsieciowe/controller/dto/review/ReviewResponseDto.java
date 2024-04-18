package org.example.lista1techsieciowe.controller.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.lista1techsieciowe.controller.dto.book.GetBookDto;
import org.example.lista1techsieciowe.controller.dto.user.GetUserDto;
import java.sql.Date;

/**
 * Data Transfer Object (DTO) representing the response for retrieving a review.
 */
public class ReviewResponseDto {

    @Schema(name = "id", example ="1" )
    private Integer reviewId;

    private GetBookDto book;

    private GetUserDto user;

    @Schema(name = "grade", example ="5" )
    private Double grade;

    @Schema(name = "comment", example ="great" )
    private String comment;

    @Schema(name = "review date", example ="2024-01-01" )
    private Date reviewDate;

    /**
     * Constructs a new ReviewResponseDto with the specified parameters.
     *
     * @param reviewId The ID of the review.
     * @param book The book associated with the review.
     * @param user The user who submitted the review.
     * @param grade The grade given to the book.
     * @param comment The comment associated with the review.
     * @param reviewDate The date the review was submitted.
     */
    public ReviewResponseDto(Integer reviewId, GetBookDto book, GetUserDto user, Double grade, String comment, Date reviewDate) {
        this.reviewId = reviewId;
        this.book = book;
        this.user = user;
        this.grade = grade;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    /**
     * Retrieves the ID of the review.
     * @return The review ID.
     */
    public Integer getReviewId() {
        return reviewId;
    }

    /**
     * Sets the ID of the review.
     * @param reviewId The review ID.
     */
    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * Retrieves the book associated with the review.
     * @return The book associated with the review.
     */
    public GetBookDto getBook() {
        return book;
    }

    /**
     * Sets the book associated with the review.
     * @param book The book associated with the review.
     */
    public void setBook(GetBookDto book) {
        this.book = book;
    }

    /**
     * Retrieves the user who submitted the review.
     * @return The user associated with the review.
     */
    public GetUserDto getUser() {
        return user;
    }

    /**
     * Sets the user who submitted the review.
     * @param user The user associated with the review.
     */
    public void setUser(GetUserDto user) {
        this.user = user;
    }

    /**
     * Retrieves the grade given to the book.
     * @return The grade.
     */
    public Double getGrade() {
        return grade;
    }

    /**
     * Sets the grade given to the book.
     * @param grade The grade.
     */
    public void setGrade(Double grade) {
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

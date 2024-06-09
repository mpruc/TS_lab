package org.example.lista1techsieciowe.controller;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.lista1techsieciowe.controller.dto.review.ReviewDto;
import org.example.lista1techsieciowe.controller.dto.review.ReviewResponseDto;
import org.example.lista1techsieciowe.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller handling operations related to reviews.
 */
@RestController
@CrossOrigin
@RequestMapping("/review")
@PreAuthorize("isAuthenticated()")
@Tag(name = "Review")

public class ReviewController {
    private final ReviewService reviewService;

    /**
     * Constructor for ReviewController.
     * @param reviewService Service responsible for review operations.
     */
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Adds a new review.
     * @param review DTO representing the review to be added.
     * @return Response containing the created review.
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    @PreAuthorize("permitAll()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review added successfully" ),
            @ApiResponse(responseCode = "404", description = "Failed to add loan", content = @Content )
    })
    public @ResponseBody ReviewResponseDto addReview(@RequestBody @Validated ReviewDto review){
        return reviewService.addReview(review);
    }

    /**
     * Retrieves all reviews.
     * @return ResponseEntity containing a list of review DTOs.
     */
    @GetMapping("/getAll")
    @PreAuthorize("permitAll()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
    })
    public ResponseEntity<List<ReviewResponseDto>> getALl() {
        List<ReviewResponseDto> dto = reviewService.getAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Retrieves details of a specific review by ID.
     * @param id ID of the review to retrieve.
     * @return ResponseEntity containing the review details.
     */
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review found" ),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content),
    })
    public ResponseEntity<ReviewResponseDto> getReview(@PathVariable Integer id) {
        ReviewResponseDto dto = reviewService.getReview(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Deletes a review by ID.
     * @param id ID of the review to delete.
     */
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully" ),
            @ApiResponse(responseCode = "404", description = "Delete failed", content = @Content),
            @ApiResponse(responseCode = "409", description = "Not authorized", content = @Content)
    })
    public void delete(@PathVariable Integer id) {
        reviewService.deleteReview(id);
    }

    /**
     * Updates a review with new information.
     * @param id ID of the review to update.
     * @param updatedReview DTO containing updated review information.
     * @return ResponseEntity containing the updated review response.
     */
    @PostMapping("/update/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review updated successfully" ),
            @ApiResponse(responseCode = "404", description = "Review update failed", content = @Content),
            @ApiResponse(responseCode = "409", description = "Not authorized", content = @Content)
    })
    public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable Integer id, @RequestBody @Validated ReviewDto updatedReview) {
        ReviewResponseDto dto = reviewService.updateReview(id, updatedReview);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}

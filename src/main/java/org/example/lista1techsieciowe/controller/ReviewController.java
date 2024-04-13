package org.example.lista1techsieciowe.controller;

import org.example.lista1techsieciowe.controller.dto.loan.GetLoanResponseDto;
import org.example.lista1techsieciowe.controller.dto.review.ReviewDto;
import org.example.lista1techsieciowe.controller.dto.review.ReviewResponseDto;
import org.example.lista1techsieciowe.entity.Review;
import org.example.lista1techsieciowe.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/review")

public class ReviewController {
    private final ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @ResponseStatus(code = HttpStatus.CREATED)

    @PostMapping("/add")
    @PreAuthorize("permitAll()")
    public @ResponseBody ReviewResponseDto addReview(@RequestBody @Validated ReviewDto review){
        return reviewService.addReview(review);
    }

    @GetMapping("/getAll")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<ReviewResponseDto>> getALl() {
        List<ReviewResponseDto> dto = reviewService.getAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ReviewResponseDto> getLoan(@PathVariable Integer id) {
        ReviewResponseDto dto = reviewService.getReview(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public void delete(@PathVariable int id) {
        reviewService.deleteReview(id);
    }

}

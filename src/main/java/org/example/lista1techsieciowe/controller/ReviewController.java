package org.example.lista1techsieciowe.controller;

import org.example.lista1techsieciowe.entity.Review;
import org.example.lista1techsieciowe.entity.User;
import org.example.lista1techsieciowe.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody Review addReview(@RequestBody Review review){
        return reviewService.addReview(review);
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable <Review> getAllReviews(){
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    public Review getReview(@PathVariable int id) {
        return reviewService.getReview(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        reviewService.deleteReview(id);
    }

}

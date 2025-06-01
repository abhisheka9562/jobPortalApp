package com.abs.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController
{
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService)
    {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> findAll(@RequestParam Long companyId)
    {
        return new ResponseEntity<>(reviewService.findAll(companyId), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId)
    {
        Review review=reviewService.getReviewById(reviewId);
        if(review!=null)
        {
            return new ResponseEntity<>(review,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody Review review,@RequestParam Long companyId)
    {
        boolean created= reviewService.createReview(companyId,review);
        if(created)
        {
            return new ResponseEntity<>("Review added successfully",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviewById(@RequestBody Review review,@PathVariable Long reviewId)
    {
        boolean updated=reviewService.updateReviewById(reviewId,review);
        if(updated)
        {
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId)
    {
        boolean deleted=reviewService.deleteReviewById(reviewId);
        if(deleted)
        {
            return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}

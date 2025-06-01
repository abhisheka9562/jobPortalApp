package com.abs.reviewms.review.impl;


import com.abs.reviewms.review.Review;
import com.abs.reviewms.review.ReviewRepository;
import com.abs.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService
{

    private ReviewRepository reviewRepository;



    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAll(Long companyId)
    {
       return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReview(Long companyId,Review review)
    {
        if(companyId!=null)
        {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReviewById(Long reviewId,Review updatedReview)
    {
       Review review=reviewRepository.findById(reviewId).orElse(null);
       if(review!=null)
       {
           review.setDescription(updatedReview.getDescription());
           review.setTitle(updatedReview.getTitle());
           review.setRating(updatedReview.getRating());
           reviewRepository.save(review);
           return true;
       }
        return false;
    }



    @Override
    public boolean deleteReviewById(Long reviewId) {
        Review review=reviewRepository.findById(reviewId).orElse(null);
        if(review!=null)
        {
          reviewRepository.delete(review);
          return true;
        }
        return false;
    }
}

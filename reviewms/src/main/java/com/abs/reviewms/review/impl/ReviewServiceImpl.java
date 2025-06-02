package com.abs.reviewms.review.impl;


import com.abs.reviewms.review.Review;
import com.abs.reviewms.review.ReviewRepository;
import com.abs.reviewms.review.ReviewService;
import com.abs.reviewms.review.clients.CompanyClient;
import com.abs.reviewms.review.external.Company;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService
{

    private ReviewRepository reviewRepository;
    private CompanyClient companyClient;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyClient companyClient) {
        this.reviewRepository = reviewRepository;
        this.companyClient = companyClient;
    }

    @Override
    public List<Review> findAll(Long companyId)
    {
       return reviewRepository.findByCompanyId(companyId);
    }

    public Company getCompanyById(Long companyId)
    {
        try {
            return companyClient.getCompanyById(companyId);
        } catch(FeignException.NotFound e){
            return null;
        }
    }

    @Override
    public boolean createReview(Long companyId,Review review)
    {
        if(companyId!=null && getCompanyById(companyId)!=null)
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

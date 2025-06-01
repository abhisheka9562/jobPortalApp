package com.abs.reviewms.review;

import java.util.List;

public interface ReviewService
{
    List<Review> findAll(Long companyId);

    boolean createReview(Long companyId,Review review);

    Review getReviewById(Long reviewId);

    boolean updateReviewById(Long reviewId,Review updatedReview);

    boolean deleteReviewById(Long reviewId);
}

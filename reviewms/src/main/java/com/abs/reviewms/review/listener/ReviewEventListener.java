package com.abs.reviewms.review.listener;

import com.abs.reviewms.review.Review;
import com.abs.reviewms.review.ReviewRepository;
import com.abs.reviewms.review.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

// Review Service Listener
@Component
public class ReviewEventListener {

    private final ReviewRepository reviewRepository;

    public ReviewEventListener(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @RabbitListener(queues = RabbitConfig.REVIEW_QUEUE)
    public void handleCompanyDeletedEvent(Long companyId) {  // Receive Long directly
        try {
            System.out.println("Processing company deletion for reviews. Company ID: " + companyId);

            List<Review> reviewsToDelete = reviewRepository.findByCompanyId(companyId);

            if (!reviewsToDelete.isEmpty()) {
                reviewRepository.deleteAll(reviewsToDelete);
                System.out.println("Deleted " + reviewsToDelete.size() + " reviews for company ID: " + companyId);
            } else {
                System.out.println("No reviews found for company ID: " + companyId);
            }

        } catch (Exception e) {
            System.err.println("Error processing company deleted event: " + e.getMessage());
        }
    }
}
package com.abs.jobms.job.clients;

import com.abs.jobms.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="REVIEWMS")
public interface ReviewClient
{
    @GetMapping("/reviews?companyId={companyId}")
    List<Review> getReviews(@PathVariable("companyId") Long companyId);
}

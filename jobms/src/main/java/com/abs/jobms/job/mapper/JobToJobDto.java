package com.abs.jobms.job.mapper;

import com.abs.jobms.job.Job;
import com.abs.jobms.job.dto.JobDto;
import com.abs.jobms.job.external.Company;
import com.abs.jobms.job.external.Review;

import java.util.List;

public  class JobToJobDto
{
    public static JobDto convertJobToJobDto(Job job, Company company, List<Review> reviews)
    {
        JobDto jobDto=new JobDto();
        jobDto.setDescription(job.getDescription());
        jobDto.setId(job.getId());
        jobDto.setLocation(job.getLocation());
        jobDto.setTitle(job.getTitle());
        jobDto.setMaxSalary(job.getMaxSalary());
        jobDto.setMinSalary(job.getMinSalary());
        jobDto.setReviews(reviews);
        jobDto.setCompany(company);
        return jobDto;
    }
}

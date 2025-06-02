package com.abs.jobms.job.impl;


import com.abs.jobms.job.Job;
import com.abs.jobms.job.JobRepository;
import com.abs.jobms.job.JobService;
import com.abs.jobms.job.clients.CompanyClient;
import com.abs.jobms.job.clients.ReviewClient;
import com.abs.jobms.job.dto.JobDto;
import com.abs.jobms.job.external.Company;
import com.abs.jobms.job.external.Review;
import com.abs.jobms.job.mapper.JobToJobDto;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService
{
    JobRepository jobRepository;
    CompanyClient companyClient;
    ReviewClient reviewClient;




    //private List<Job> jobs=new ArrayList<>();
    //private Long nextId =1L;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }



    @Override
    public List<JobDto> findAll()
    {
        List<Job> jobs=jobRepository.findAll();
        List<JobDto> jobDtos =new ArrayList<>();
        for(Job job:jobs)
        {
            JobDto jobDto =convertTojobDTO(job);
            jobDtos.add(jobDto);
        }
        return jobDtos;
    }


    public JobDto convertTojobDTO(Job job)
    {
        if(job==null)
        {
            return null;
        }
        Company company=companyClient.getCompanyById(job.getCompanyId());
        List<Review> reviews=reviewClient.getReviews(job.getCompanyId());
        return JobToJobDto.convertJobToJobDto(job,company,reviews);
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
    public boolean createJob(Job job)
    {
        if(getCompanyById(job.getCompanyId())==null)
        {
            return false;
        }
        jobRepository.save(job);
        return true;
    }

    @Override
    public JobDto getJobById(Long id)
    {
        Job job=jobRepository.findById(id).orElse(null);
        return convertTojobDTO(job);
    }

    @Override
    public boolean deleteJobById(Long id)
    {
        Job job=jobRepository.findById(id).orElse(null);
        if(job!=null)
        {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob)
    {
        Optional<Job> jobOptional=jobRepository.findById(id);
        if(jobOptional.isPresent())
        {
            Job job=jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}

package com.abs.jobms.job;

import com.abs.jobms.job.dto.JobDto;

import java.util.List;

public interface JobService
{
  List<JobDto> findAll();
  boolean createJob(Job job);

  JobDto getJobById(Long id);

  boolean deleteJobById(Long id);

  boolean updateJobById(Long id, Job updatedJob);
}

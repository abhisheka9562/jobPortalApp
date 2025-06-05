package com.abs.jobms.job.listener;

import com.abs.jobms.job.Job;
import com.abs.jobms.job.JobRepository;
import com.abs.jobms.job.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

// Job Service Listener
@Component
public class JobEventListener {

    private final JobRepository jobRepository;

    public JobEventListener(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @RabbitListener(queues = RabbitConfig.JOB_QUEUE)
    public void handleCompanyDeletedEvent(Long companyId) {  // Receive Long directly
        try {
            System.out.println("Processing company deletion for jobs. Company ID: " + companyId);

            List<Job> jobsToDelete = jobRepository.findByCompanyId(companyId);

            if (!jobsToDelete.isEmpty()) {
                jobRepository.deleteAll(jobsToDelete);
                System.out.println("Deleted " + jobsToDelete.size() + " jobs for company ID: " + companyId);
            } else {
                System.out.println("No jobs found for company ID: " + companyId);
            }

        } catch (Exception e) {
            System.err.println("Error processing company deleted event: " + e.getMessage());
        }
    }
}
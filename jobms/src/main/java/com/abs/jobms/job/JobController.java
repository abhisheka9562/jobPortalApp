package com.abs.jobms.job;

import com.abs.jobms.job.dto.JobDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController
{

    private JobService jobservice;

    public JobController(JobService jobservice)
    {
        this.jobservice = jobservice;
    }

    @GetMapping
    public ResponseEntity<List<JobDto>> findAll()
    {
        return new ResponseEntity<>(jobservice.findAll(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job)
    {
        boolean created=jobservice.createJob(job);
        if(created) {
            return new ResponseEntity<>("Job added successfully",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id)
    {
        JobDto jobDto=jobservice.getJobById(id);
        if(jobDto!=null)
            return new ResponseEntity<>(jobDto, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id)
    {
        boolean deleted=jobservice.deleteJobById(id);
        if(deleted)
            return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id,@RequestBody Job updatedJob)
    {
        boolean updated=jobservice.updateJobById(id,updatedJob);
        if(updated)
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

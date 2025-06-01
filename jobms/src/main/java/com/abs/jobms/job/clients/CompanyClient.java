package com.abs.jobms.job.clients;

import com.abs.jobms.job.external.Company;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="COMPANYMS")
public interface CompanyClient
{
    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable("id") Long id);
}

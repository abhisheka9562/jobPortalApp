package com.abs.reviewms.review.clients;

import com.abs.reviewms.review.external.Company;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANYMS")
public interface CompanyClient
{
    @GetMapping("/companies/{id}")
    Company getCompanyById(@PathVariable("id") Long id);
}

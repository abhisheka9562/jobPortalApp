package com.abs.companyms.company.impl;


import com.abs.companyms.company.Company;
import com.abs.companyms.company.CompanyRepository;
import com.abs.companyms.company.CompanyService;
import com.abs.companyms.company.publisher.CompanyEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService
{
   private CompanyRepository companyRepository;
   private CompanyEventPublisher companyEventPublisher;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyEventPublisher companyEventPublisher) {
        this.companyRepository = companyRepository;
        this.companyEventPublisher = companyEventPublisher;
    }

    @Override
    public List<Company> findAll()
    {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompanyById(Company updatedCompany, Long id)
    {
        Company company=companyRepository.findById(id).orElse(null);
        if(company!=null)
        {
            company.setDescription(updatedCompany.getDescription());
            company.setName(updatedCompany.getName());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company)
    {
       companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id)
    {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id)
    {
       try {
           if (companyRepository.existsById(id)) {
               companyRepository.deleteById(id);
               companyEventPublisher.publishCompanyDeletedEvent(id);
               return true;
           }
           return false;
       } catch (Exception e) {
           return false;
       }
    }
}

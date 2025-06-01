package com.abs.companyms.company;

import java.util.List;

public interface CompanyService
{
    List<Company> findAll();

    boolean updateCompanyById(Company updatedCompany, Long id);

    void createCompany(Company company);

    Company getCompanyById(Long id);

    boolean deleteCompanyById(Long id);
}

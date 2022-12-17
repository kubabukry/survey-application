package com.example.survey.service;

import com.example.survey.model.Company;
import com.example.survey.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public List<Company> getCompanys() {
        return companyRepository.findAll();
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    //getCompany()
    //deleteCompany()
    //updateCompany()
    //verifyCompany()
    //createCompany(): addCompany()
    //addSurvey()
}

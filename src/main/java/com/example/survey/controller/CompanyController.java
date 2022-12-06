package com.example.survey.controller;

import com.example.survey.model.Company;
import com.example.survey.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companys")
    public List<Company> getCompanys(){
        return companyService.getCompanys();
    }

    @PostMapping("/companys")
    public void addCompany(@RequestBody Company company){
        companyService.addCompany(company);
    }
}

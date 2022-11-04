package com.example.survey.controller;

import com.example.survey.model.Company;
import com.example.survey.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/companys")
    public List<Company> getCompanys(){
        return companyService.getCompanys();
    }

    @PostMapping("/companys")
    public Company addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }
}

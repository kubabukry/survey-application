package com.example.survey.controller;

import static com.example.survey.mapper.CompanyMapper.mapCompanyListToCompanyDtoList;
import static com.example.survey.mapper.CompanyMapper.mapCompanyToCompanyDto;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.survey.dto.CompanyCreationDto;
import com.example.survey.dto.CompanyDto;
import com.example.survey.dto.CompanyVerificationDto;
import com.example.survey.service.CompanyService;

@RestController
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public List<CompanyDto> getCompanies(){
        return mapCompanyListToCompanyDtoList(companyService.getCompanies());
    }

    @PostMapping("/companies")
    public CompanyDto createCompany(@Valid @RequestBody CompanyCreationDto companyCreationDto){
        return mapCompanyToCompanyDto(companyService.createCompany(companyCreationDto));
    }

    @PutMapping("/companies/{id}")
    public CompanyDto updateCompany(@Valid @RequestBody CompanyDto companyDto){
        return mapCompanyToCompanyDto(companyService.updateCompany(companyDto));
    }

    @GetMapping("/companies/{id}")
    public CompanyDto getSingleCompany(@PathVariable Long id){
        return mapCompanyToCompanyDto(companyService.getSingleCompany(id));
    }

    @PutMapping("/companies/{id}/verify")
    public void verifyCompany(@Valid @RequestBody CompanyVerificationDto companyVerificationDto){
        companyService.verifyCompany(companyVerificationDto);
    }
    @DeleteMapping("/companies/{id}")
    public void deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
    }

    @GetMapping("/companies/category/{categoryId}")
        public List<CompanyDto> getCompaniesCategory(@PathVariable Long categoryId){
            return mapCompanyListToCompanyDtoList(companyService.getCompaniesByCategoryId(categoryId));
        }
}
package com.example.survey.controller;

import com.example.survey.dto.CompanyCreationDto;
import com.example.survey.dto.CompanyDto;
import com.example.survey.dto.CompanyVerificationDto;
import com.example.survey.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.survey.mapper.CompanyMapper.mapCompanyListToCompanyDtoList;
import static com.example.survey.mapper.CompanyMapper.mapCompanyToCompanyDto;

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

    @GetMapping("/companies/users/{id}")
    public CompanyDto getCompanyByUser(@PathVariable Long id){
        return mapCompanyToCompanyDto(companyService.getCompanyByUser(id));
    }

    @GetMapping("/companies/category/{categoryId}")
        public List<CompanyDto> getCompaniesCategory(@PathVariable Long categoryId){
            return mapCompanyListToCompanyDtoList(companyService.getCompaniesByCategoryId(categoryId));
        }
}
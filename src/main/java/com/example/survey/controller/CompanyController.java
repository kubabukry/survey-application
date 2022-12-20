package com.example.survey.controller;

import com.example.survey.dto.CompanyCreationDto;
import com.example.survey.dto.CompanyDto;
import com.example.survey.dto.CompanyVerificationDto;
import com.example.survey.exception.*;
import com.example.survey.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public CompanyDto createCompany(@RequestBody CompanyCreationDto companyCreationDto){
        return mapCompanyToCompanyDto(companyService.createCompany(companyCreationDto));
    }

    @PutMapping("/company/{id}")
    public CompanyDto updateCompany(@RequestBody CompanyDto companyDto){
        return mapCompanyToCompanyDto(companyService.updateCompany(companyDto));
    }

    @GetMapping("/company/{id}")
    public CompanyDto getSingleCompany(@PathVariable Long id){
        return mapCompanyToCompanyDto(companyService.getSingleCompany(id));
    }

    @PutMapping("/company/{id}/verify")
    public void verifyCompany(@RequestBody CompanyVerificationDto companyVerificationDto){
        companyService.verifyCompany(companyVerificationDto);
    }
    @DeleteMapping("/company/{id}")
    public void deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
    }

    @ExceptionHandler(value = CompanyNameAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCompanyNameAlreadyInUseException(CompanyNameAlreadyInUseException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = CompanyNipAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCompanyNipAlreadyInUseException(CompanyNipAlreadyInUseException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchCompanyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchCompanyExistsException(NoSuchCompanyExistsException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchRegisteredUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchRegisteredUserException(NoSuchRegisteredUserException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
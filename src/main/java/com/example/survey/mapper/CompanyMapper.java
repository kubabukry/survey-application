package com.example.survey.mapper;

import com.example.survey.dto.CompanyDto;
import com.example.survey.model.Company;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyMapper {

    public static List<CompanyDto> mapCompanyListToCompanyDtoList(List<Company> companyList){
        return companyList.stream()
                .map(company -> new CompanyDto(
                        company.getId(),
                        company.getName(),
                        company.getNip(),
                        company.getAddress(),
                        company.getIsVerified(),
                        company.getIdUser().getId()))
                .collect(Collectors.toList());
    }

    public static CompanyDto mapCompanyToCompanyDto(Company company){
        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getNip(),
                company.getAddress(),
                company.getIsVerified(),
                company.getIdUser().getId()
        );
    }
}

package com.example.survey.service;

import com.example.survey.dto.CompanyCreationDto;
import com.example.survey.dto.CompanyDto;
import com.example.survey.exception.CompanyNameAlreadyInUseException;
import com.example.survey.exception.CompanyNipAlreadyInUseException;
import com.example.survey.exception.NoSuchRegisteredUserException;
import com.example.survey.model.Company;
import com.example.survey.model.RegisteredUser;
import com.example.survey.repository.CompanyRepository;
import com.example.survey.repository.RegisteredUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final RegisteredUserRepository registeredUserRepository;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company createCompany(CompanyCreationDto companyCreationDto) {
        Boolean nameExists = companyRepository.existsByName(companyCreationDto.name());
        Boolean nipExists = companyRepository.existsByNip(companyCreationDto.nip());

        if(nameExists){
            throw new CompanyNameAlreadyInUseException("Company with name: "+companyCreationDto.name()+" already exists");
        }
        if(nipExists){
            throw new CompanyNipAlreadyInUseException("Company with nip: "+companyCreationDto.nip()+" already exists");
        }
        RegisteredUser registeredUser = registeredUserRepository.findById(companyCreationDto.idUser())
                .orElseThrow(() -> new NoSuchRegisteredUserException(
                        "No such user with id = "+companyCreationDto.idUser()+" exists"));

        Company company = new Company();
        company.setName(companyCreationDto.name());
        company.setAddress(companyCreationDto.address());
        company.setNip(companyCreationDto.nip());
        company.setIdUser(registeredUser);
        company.setIsVerified(false);
        return companyRepository.save(company);
    }

    //getCompany()
    //deleteCompany()
    //updateCompany()
    //verifyCompany()
    //createCompany() +
    //addSurvey()

    //getCompanies() +

    //todo z jakiegos powodu przypisuje null do pola user
}

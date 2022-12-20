package com.example.survey.service;

import com.example.survey.dto.CompanyCreationDto;
import com.example.survey.dto.CompanyDto;
import com.example.survey.exception.CompanyNameAlreadyInUseException;
import com.example.survey.exception.CompanyNipAlreadyInUseException;
import com.example.survey.exception.NoSuchCompanyExistsException;
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

    //todo po implementacji logowania bedzie pobieralo zalogowanego usera jako wlascicela danej firmy
    //poki co trzeba manulanie podac id, wtedy bedzie przypisywalo tego ktory utworzyl po zalogowaniu
    //todo teraz jeden user moze posiadac jedna firme, czy tak powinno byc?
    //nie ma na to wyjatku poki co bo nie wiem czy jest potrzebny
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

    //todo utworzyc CompanyUpdateDto bez podawania isVerified? bez sensu powielanie tego w verifyCompany
    //dodatkowo user nie powinien miec mozliwosci weryfikacji firmy tylko admin/moderator
    public Company updateCompany(CompanyDto companyDto) {
        Company company = companyRepository.findById(companyDto.id())
                .orElseThrow(() -> new NoSuchCompanyExistsException(
                        "No company with id = "+companyDto.id()+" exists"));

        Boolean nameExists = companyRepository.existsByName(companyDto.name());
        Boolean nipExists = companyRepository.existsByNip((companyDto.nip()));
        if(nameExists)
            throw new CompanyNameAlreadyInUseException("Company with name: "+companyDto.name()+" already exists");
        if(nipExists)
            throw new CompanyNipAlreadyInUseException("Company with nip: "+companyDto.nip()+" already exists");

        RegisteredUser registeredUser = registeredUserRepository.findById(companyDto.idUser())
                .orElseThrow(() -> new NoSuchRegisteredUserException("" +
                        "No such user with id = "+companyDto.idUser()+" exists"));

        company.setId(companyDto.id());
        company.setAddress(companyDto.address());
        company.setIsVerified(companyDto.isVerified());
        company.setName(companyDto.name());
        company.setNip(companyDto.nip());
        company.setIdUser(registeredUser);
        return companyRepository.save(company);
    }

    public Company getSingleCompany(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new NoSuchCompanyExistsException("No such company with id = "+id));
    }


    //getSingleCompany +
    //deleteCompany()
    //updateCompany() +
    //verifyCompany()
    //createCompany() +
    //addSurvey()

    //getCompanies() +

    //rozwiazane
    //z jakiegos powodu przypisuje null do pola user (rozwiazane, pole bylo updatable = false i insertable = false)
}

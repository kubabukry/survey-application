package com.example.survey.repository;

import com.example.survey.model.Company;
import com.example.survey.model.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Boolean existsByName(String name);
    Boolean existsByNip(String nip);

    Company findByIdUser(RegisteredUser registeredUser);
}

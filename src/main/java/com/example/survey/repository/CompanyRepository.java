package com.example.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.survey.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Boolean existsByName(String name);
    Boolean existsByNip(String nip);
}

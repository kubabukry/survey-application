package com.example.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.survey.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findDistinctByName(String name);
    Boolean existsByName(String name);
}

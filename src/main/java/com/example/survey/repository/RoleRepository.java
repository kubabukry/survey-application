package com.example.survey.repository;

import com.example.survey.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findDistinctByName(String name);
    Role findDistinctById(Long id);
}

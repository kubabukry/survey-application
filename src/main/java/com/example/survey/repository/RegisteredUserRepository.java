package com.example.survey.repository;

import com.example.survey.model.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// kom

@Repository     //not necessary as JpaRepository includes it
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
    RegisteredUser findDistinctById(Long id);
    Optional<RegisteredUser> findDistinctByLogin(String login);

}

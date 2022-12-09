package com.example.survey.service;

import com.example.survey.dto.RoleNameDto;
import com.example.survey.model.Role;
import com.example.survey.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role addRole(RoleNameDto roleNameDto) {
        Role role = new Role();
        role.setName(roleNameDto.name());
        return roleRepository.save(role);
    }

    public Role getRoleByName(String name){
       return roleRepository.findDistinctByName(name);
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    //setRegisteredUserRole()
    //createRole(): addRole() +
    //updateRole()
    //deleteRole():
    //getRole(): getSingleRole() +

    //dodatkowo getRoles()
}

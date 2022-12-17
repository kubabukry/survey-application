package com.example.survey.service;

import com.example.survey.dto.*;
import com.example.survey.model.RegisteredUser;
import com.example.survey.model.Role;
import com.example.survey.repository.RegisteredUserRepository;
import com.example.survey.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RegisteredUserRepository registeredUserRepository;

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

    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }

    public Role updateRole(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.id());
        role.setName(roleDto.name());
        return roleRepository.save(role);
    }

    public RegisteredUser setRegisteredUserRole(RegisteredUserRoleDto registeredUserRoleDto){
        RegisteredUser registeredUser = registeredUserRepository.findDistinctById(registeredUserRoleDto.userId());
        Role role = roleRepository.findDistinctById(registeredUserRoleDto.roleId());
        registeredUser.setRole(role);
        return registeredUserRepository.save(registeredUser);
    }


    //todo zrobic obsluge wyjatkow

    //setRegisteredUserRole() ?? dodawanie po samym id albo nazwie?
    //createRole(): addRole() +
    //updateRole() +
    //deleteRole() +
    //getRole(): getSingleRole() +

    //dodatkowo getRoles() +
}

package com.example.survey.service;

import com.example.survey.dto.*;
import com.example.survey.exception.NoSuchRoleExistsException;
import com.example.survey.exception.RoleAlreadyExistsException;
import com.example.survey.exception.RoleIsInUseException;
import com.example.survey.model.RegisteredUser;
import com.example.survey.model.Role;
import com.example.survey.repository.RegisteredUserRepository;
import com.example.survey.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RegisteredUserRepository registeredUserRepository;

    private final RegisteredUserService registeredUserService;

    //sprawdza czy rola o danej nazwie juz istnieje, jesli tak wyrzuca wyjatek
    public Role addRole(RoleNameDto roleNameDto) {
        Boolean roleExists = roleRepository.existsByName(roleNameDto.name());
        if(roleExists == false){
            Role role = new Role();
            role.setName(roleNameDto.name());
            return roleRepository.save(role);
        }
        else
            throw new RoleAlreadyExistsException("Role already exists");
    }

//    public Role getRoleByName(String name){
//       return roleRepository.findByName(name);
//    }

    //nie ma wyjatkow, zwroci pusta liste
    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    //sprawdza czy istnieje, jesli tak to usuwa
    public void deleteRole(Long id){
        Role role = roleRepository.findById(id).orElseThrow(() -> new NoSuchRoleExistsException(
                "No role present with id = "+id));
        if(roleInUse(id)==true)
            throw new RoleIsInUseException("Role "+role.getName()+" is still in use");
        roleRepository.deleteById(id);
    }

    //najpierw sprawdza czy rola istnieje po id
    public Role updateRole(RoleDto roleDto) {
        Role role = roleRepository.findById(roleDto.id())
                .orElseThrow(() -> new NoSuchRoleExistsException("No role present with id = "+roleDto.id()));
        role.setName(roleDto.name());
        return roleRepository.save(role);
    }

    public RegisteredUser setRegisteredUserRole(RegisteredUserRoleDto registeredUserRoleDto){
        RegisteredUser registeredUser = registeredUserRepository.findDistinctById(registeredUserRoleDto.userId());

        //wyjatek tak naprawde obsluzony linijke wyzej, z wlasciwym userem rola nigdy null
        Role role = roleRepository.findById(registeredUserRoleDto.roleId()).orElseThrow();
        registeredUser.setRole(role);
        return registeredUserRepository.save(registeredUser);
    }

    //sprawdza czy rola z danym id istnieje
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchRoleExistsException("No role present with id = "+id));
    }

    private Boolean roleInUse(Long id){
        List<RegisteredUser> registeredUsersUsingRole = registeredUserService.getRegisteredUsers()
                .stream()
                .filter(registeredUser -> registeredUser.getRole().getId()==id)
                .collect(Collectors.toList());
        if(registeredUsersUsingRole.isEmpty())
            return false;
        else
            return true;
    }


    //todo zrobic obsluge wyjatkow

    //setRegisteredUserRole() +
    //createRole(): addRole() +
    //updateRole() +
    //deleteRole() +
    //getRole(): getSingleRole() +
    //getRoleById() +

    //dodatkowo getRoles() +
}

package com.example.survey.controller;

import static com.example.survey.mapper.RoleMapper.mapRoleListToRoleDtoList;
import static com.example.survey.mapper.RoleMapper.mapRoleToRoleDto;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.survey.dto.RegisteredUserRoleDto;
import com.example.survey.dto.RoleDto;
import com.example.survey.dto.RoleNameDto;
import com.example.survey.service.RoleService;

@RestController
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/roles")
    public void createRole(@Valid @RequestBody RoleNameDto roleNameDto){
        roleService.addRole(roleNameDto);
    }

    @GetMapping("/roles/{id}")
    public RoleDto getRoleById(@PathVariable Long id){
        return mapRoleToRoleDto(roleService.getRoleById(id));
    }

    @GetMapping("/roles")
    public List<RoleDto> getRoles(){
        return mapRoleListToRoleDtoList(roleService.getRoles());
    }

    @DeleteMapping("/roles/{id}")
    public void deleteRole(@PathVariable Long id){
        roleService.deleteRole(id);
    }

    @PutMapping("/roles/{id}")
    public void updateRole(@Valid @RequestBody RoleDto roleDto){
        roleService.updateRole(roleDto);
    }

    @PutMapping("/roles/user")       //jaki endpoint?
    public void setRegisteredUserRole(@Valid @RequestBody RegisteredUserRoleDto registeredUserRoleDto){
        roleService.setRegisteredUserRole(registeredUserRoleDto);
    }
}

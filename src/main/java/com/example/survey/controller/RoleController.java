package com.example.survey.controller;

import com.example.survey.dto.RoleDto;
import com.example.survey.dto.RoleNameDto;
import com.example.survey.model.Role;
import com.example.survey.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.survey.mapper.RoleMapper.mapRoleToRoleDto;

@RestController
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/roles")
    public void createRole(@RequestBody RoleNameDto roleNameDto){
        roleService.addRole(roleNameDto);
    }

    @GetMapping("/role/{name}")
    public RoleDto getRoleByName(@PathVariable String name){
        return mapRoleToRoleDto(roleService.getRoleByName(name));
    }

    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }
    
}

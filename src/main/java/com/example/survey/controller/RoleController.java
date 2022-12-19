package com.example.survey.controller;

import com.example.survey.dto.RegisteredUserRoleDto;
import com.example.survey.dto.RoleDto;
import com.example.survey.dto.RoleNameDto;
import com.example.survey.exception.ErrorResponse;
import com.example.survey.exception.NoSuchRoleExistsException;
import com.example.survey.exception.RoleAlreadyExistsException;
import com.example.survey.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.survey.mapper.RoleMapper.mapRoleListToRoleDtoList;
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

//    @GetMapping("/role/{name}")
//    public RoleDto getRoleByName(@PathVariable String name){
//        return mapRoleToRoleDto(roleService.getRoleByName(name));
//    }

    @GetMapping("/role/{id}")
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
    public void updateRole(@RequestBody RoleDto roleDto){
        roleService.updateRole(roleDto);
    }

    @PutMapping("/roles/user")       //jaki endpoint?
    public void setRegisteredUserRole(@RequestBody RegisteredUserRoleDto registeredUserRoleDto){
        roleService.setRegisteredUserRole(registeredUserRoleDto);
    }

    //handle NoSuchRoleExistsException
    @ExceptionHandler(value = NoSuchRoleExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchRoleExistsException(NoSuchRoleExistsException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    //handle RoleAlreadyExistException
    @ExceptionHandler(value = RoleAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleRoleAlreadyExistsException(RoleAlreadyExistsException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }
    
    //todo sprawdzic czy dobry http error code

}

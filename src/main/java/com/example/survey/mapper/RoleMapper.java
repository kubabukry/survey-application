package com.example.survey.mapper;

import com.example.survey.dto.RoleDto;
import com.example.survey.model.Role;

public class RoleMapper {
    public static RoleDto mapRoleToRoleDto(Role role){
        return new RoleDto(
                role.getId(),
                role.getName()
        );
    }

}

package com.example.survey.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.survey.dto.RoleDto;
import com.example.survey.model.Role;

public class RoleMapper {
    public static RoleDto mapRoleToRoleDto(Role role){
        return new RoleDto(
                role.getId(),
                role.getName()
        );
    }

    public static List<RoleDto> mapRoleListToRoleDtoList(List<Role> roleList){
        return roleList.stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toList());
    }

}

package com.sso.auth.mapper;

import com.sso.auth.model.Role;
import com.sso.auth.payload.Role.RoleDto;
import com.sso.auth.payload.Role.RoleList;

public class RoleMapper {
    public static Role mapToRole(RoleDto request){
        Role role = new Role();
        role.setRoleName(request.getRoleName());
        return role;
    }
    public static RoleDto mapToRoleDto(Role request){
        RoleDto roleDto = new RoleDto();
        roleDto.setId(request.getId());
        roleDto.setRoleName(request.getRoleName());
        return roleDto;
    }
    public static RoleList mapToRoleList(Role request){
        RoleList roleList = new RoleList();
        roleList.setId(request.getId());
        roleList.setRoleName(request.getRoleName());
        return roleList;
    }
}

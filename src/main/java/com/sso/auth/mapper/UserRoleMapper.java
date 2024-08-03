package com.sso.auth.mapper;

import com.sso.auth.model.UserRole;
import com.sso.auth.payload.userRole.UserRoleDto;

public class UserRoleMapper {
    public static UserRole mapToUserRole(UserRoleDto request){
        UserRole userRole = new UserRole();
        userRole.setUserId(request.getUserId());
        userRole.setRoleId(request.getRoleId());
        return userRole;
    }
    public static UserRoleDto mapToUserRoleDto(UserRole request){
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setId(request.getId());
        userRoleDto.setUserId(request.getUserId());
        userRoleDto.setRoleId(request.getRoleId());
        return userRoleDto;
    }
}

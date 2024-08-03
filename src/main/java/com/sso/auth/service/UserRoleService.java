package com.sso.auth.service;

import com.sso.auth.payload.userRole.UserRoleDto;

public interface UserRoleService {
    UserRoleDto saveUserRole(String serviceId, UserRoleDto request);
}

package com.sso.auth.service;

import com.sso.auth.payload.Role.RoleDto;
import com.sso.auth.payload.Role.RoleListResponse;

public interface RoleService {
    RoleDto saveRole(String serviceId, RoleDto request);
    RoleListResponse roleList(String serviceId);
}

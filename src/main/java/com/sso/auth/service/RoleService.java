package com.sso.auth.service;

import com.sso.auth.payload.role.RoleDto;
import com.sso.auth.payload.role.RoleListResponse;

public interface RoleService {
    RoleDto saveRole(String serviceId, RoleDto request);
    RoleListResponse roleList(String serviceId);
}

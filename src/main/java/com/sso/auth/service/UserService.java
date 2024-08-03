package com.sso.auth.service;

import com.sso.auth.payload.user.UserDto;
import com.sso.auth.payload.user.UserListResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {
    UserDto saveUser(String serviceId, UserDto request);
    UserListResponse getAllUser(String serviceId);
}

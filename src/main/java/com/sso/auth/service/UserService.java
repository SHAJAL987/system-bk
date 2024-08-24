package com.sso.auth.service;

import com.sso.auth.payload.credentials.LoginDto;
import com.sso.auth.payload.credentials.LoginResponse;
import com.sso.auth.payload.user.UserDto;
import com.sso.auth.payload.user.UserListResponse;

public interface UserService {
    UserDto saveUser(String serviceId, UserDto request);
    UserListResponse getAllUser(String serviceId);
    LoginResponse verify(LoginDto loginDto);
}

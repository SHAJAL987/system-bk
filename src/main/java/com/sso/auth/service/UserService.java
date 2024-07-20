package com.sso.auth.service;

import com.sso.auth.payload.User.UserDto;
import com.sso.auth.payload.User.UserListResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {
    UserDto saveUser(String serviceId, UserDto request);
    UserListResponse getAllUser(String serviceId);
}

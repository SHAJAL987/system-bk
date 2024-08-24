package com.sso.auth.serviceImp;

import com.sso.auth.Utilities.CredentialsManager;
import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.mapper.LogingMapper;
import com.sso.auth.mapper.UserMapper;
import com.sso.auth.model.User;
import com.sso.auth.payload.credentials.LoginDto;
import com.sso.auth.payload.credentials.LoginResponse;
import com.sso.auth.payload.credentials.ParamOne;
import com.sso.auth.payload.user.*;
import com.sso.auth.repository.UserRepository;
import com.sso.auth.repository.UserRoleRepository;
import com.sso.auth.service.JWTService;
import com.sso.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    JWTService jwtService;

    @Override
    public UserDto saveUser(String serviceId, UserDto request) {
        request.setPassword(CredentialsManager.passwordEncoder(request.getPassword()));
        UserDto userDto = new UserDto();
        User user = UserMapper.mapToUser(request);
        user = userRepository.save(user);
        userDto = UserMapper.mapToUserDto(user);
        userDto.setCorrelationId(serviceId);
        userDto.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
        userDto.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
        return userDto;
    }

    @Override
    public UserListResponse getAllUser(String serviceId) {
        UserListResponse userListResponse = new UserListResponse();
        List<User> user = userRepository.findAll();
        List<UserList> userLists = user.stream().map(userMap -> UserMapper.mapToUserList(userMap)).collect(Collectors.toList());
        userListResponse.setUserList(userLists);
        userListResponse.setCorrelationId(serviceId);
        userListResponse.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
        userListResponse.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
        return userListResponse;
    }

    @Override
    public LoginResponse verify(LoginDto loginDto) {
        LoginResponse response = new LoginResponse();
        ParamOne paramOne = null;
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(loginDto.getMail(),loginDto.getPassword()));
        if (authentication.isAuthenticated()){
            Object userBasic = userRoleRepository.userBasicInfo(loginDto.getMail());
            if (userBasic instanceof Object[]) {
                paramOne = LogingMapper.convertToParamOne((Object[]) userBasic);
            }
            response.setUserBasic(paramOne);
            response.setToken(jwtService.generateToken(loginDto.getMail()));
        }
        return response;
    }

}

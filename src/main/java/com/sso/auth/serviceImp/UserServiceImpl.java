package com.sso.auth.serviceImp;

import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.mapper.UserMapper;
import com.sso.auth.model.User;
import com.sso.auth.payload.user.UserDto;
import com.sso.auth.payload.user.UserList;
import com.sso.auth.payload.user.UserListResponse;
import com.sso.auth.repository.UserRepository;
import com.sso.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public UserDto saveUser(String serviceId, UserDto request) {
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

}

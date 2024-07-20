package com.sso.auth.serviceImp;

import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.mapper.RoleMapper;
import com.sso.auth.mapper.UserMapper;
import com.sso.auth.model.User;
import com.sso.auth.payload.User.UserDto;
import com.sso.auth.payload.User.UserList;
import com.sso.auth.payload.User.UserListResponse;
import com.sso.auth.repository.UserRepository;
import com.sso.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        try{
            User user = UserMapper.mapToUser(request);
            user = userRepository.save(user);
            userDto = UserMapper.mapToUserDto(user);
            userDto.setCorrelationId(serviceId);
            userDto.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
            userDto.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
        }catch (Exception ex){
            userDto.setCorrelationId(serviceId);
            userDto.setResponseCode(ResponseEnum.ResponseCode.DB_ERROR.getCode());
            userDto.setResponseMessage(ResponseEnum.ResponseCode.DB_ERROR.getMessage());
        }
        return userDto;
    }

    @Override
    public UserListResponse getAllUser(String serviceId) {
        UserListResponse userListResponse = new UserListResponse();
        try{
            List<User> user = userRepository.findAll();
            List<UserList> userLists = user.stream().map(userMap-> UserMapper.mapToUserList(userMap)).collect(Collectors.toList());
            userListResponse.setUserList(userLists);
            userListResponse.setCorrelationId(serviceId);
            userListResponse.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
            userListResponse.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
        }catch (Exception ex){
            userListResponse.setCorrelationId(serviceId);
            userListResponse.setResponseCode(ResponseEnum.ResponseCode.DB_ERROR.getCode());
            userListResponse.setResponseMessage(ResponseEnum.ResponseCode.DB_ERROR.getMessage());
        }
        return userListResponse;
    }

}

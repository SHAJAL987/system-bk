package com.sso.auth.mapper;

import com.sso.auth.model.User;
import com.sso.auth.payload.User.UserDto;
import com.sso.auth.payload.User.UserList;

public class UserMapper {
    public static User mapToUser(UserDto request){
        User user = new User();
        user.setMail(request.getMail());
        user.setPassword(request.getPassword());
        user.setStatus(request.getStatus());
        return user;
    }
    public static UserDto mapToUserDto(User request){
        UserDto userDto = new UserDto();
        userDto.setId(request.getId());
        userDto.setMail(request.getMail());
        userDto.setPassword("******");
        userDto.setStatus(request.getStatus());
        return userDto;
    }
    public static UserList mapToUserList(User request){
        UserList userList = new UserList();
        userList.setId(request.getId());
        userList.setMail(request.getMail());
        userList.setStatus(request.getStatus());
        return userList;
    }
}

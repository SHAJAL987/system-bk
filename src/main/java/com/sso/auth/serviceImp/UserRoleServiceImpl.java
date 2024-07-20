package com.sso.auth.serviceImp;

import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.mapper.UserRoleMapper;
import com.sso.auth.model.UserRole;
import com.sso.auth.payload.UserRole.UserRoleDto;
import com.sso.auth.repository.UserRoleRepository;
import com.sso.auth.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@AllArgsConstructor
@Service
public class UserRoleServiceImpl implements UserRoleService {
    private UserRoleRepository userRoleRepository;

    @Override
    public UserRoleDto saveUserRole(String serviceId, UserRoleDto request) {
        UserRoleDto response = new UserRoleDto();
        try {
            int roleId = request.getRoleId();
            int userId = request.getUserId();
            int count = userRoleRepository.findByRoleIdAndUserIdCustom(roleId, userId);
            if (count>0){
                response.setCorrelationId(serviceId);
                response.setResponseCode(ResponseEnum.ResponseCode.ROLE_USER_EXIST.getCode());
                response.setResponseMessage(ResponseEnum.ResponseCode.ROLE_USER_EXIST.getMessage());
            }else {
                UserRole userRole = UserRoleMapper.mapToUserRole(request);
                userRole = userRoleRepository.save(userRole);
                response = UserRoleMapper.mapToUserRoleDto(userRole);
                response.setCorrelationId(serviceId);
                response.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
                response.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
            }
        } catch (Exception ex) {
            response.setCorrelationId(serviceId);
            response.setResponseCode(ResponseEnum.ResponseCode.DB_ERROR.getCode());
            response.setResponseMessage(ResponseEnum.ResponseCode.DB_ERROR.getMessage());
        }
        return response;
    }
}

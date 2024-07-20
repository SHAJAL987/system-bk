package com.sso.auth.serviceImp;

import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.mapper.RoleMapper;
import com.sso.auth.model.Role;
import com.sso.auth.payload.Role.RoleDto;
import com.sso.auth.payload.Role.RoleList;
import com.sso.auth.payload.Role.RoleListResponse;
import com.sso.auth.repository.RoleRepository;
import com.sso.auth.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    @Override
    public RoleDto saveRole(String serviceId, RoleDto request) {
        RoleDto roleResponse = new RoleDto();
        try{
            Role role = RoleMapper.mapToRole(request);
            role = roleRepository.save(role);
            roleResponse = RoleMapper.mapToRoleDto(role);
            roleResponse.setCorrelationId(serviceId);
            roleResponse.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
            roleResponse.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
        }catch (Exception ex){
            roleResponse.setCorrelationId(serviceId);
            roleResponse.setResponseCode(ResponseEnum.ResponseCode.DB_ERROR.getCode());
            roleResponse.setResponseMessage(ResponseEnum.ResponseCode.DB_ERROR.getMessage());
            System.out.println(ex.getStackTrace());
        }
        return roleResponse;
    }

    @Override
    public RoleListResponse roleList(String serviceId) {
        RoleListResponse roleListResponse = new RoleListResponse();
        try{
            List<Role> roleList = roleRepository.findAll();
            List<RoleList> roleLists = roleList.stream().map(roleListMap->RoleMapper.mapToRoleList(roleListMap)).collect(Collectors.toList());
            roleListResponse.setRoleList(roleLists);
            roleListResponse.setCorrelationId(serviceId);
            roleListResponse.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
            roleListResponse.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
        }catch (Exception ex){
            roleListResponse.setCorrelationId(serviceId);
            roleListResponse.setResponseCode(ResponseEnum.ResponseCode.DB_ERROR.getCode());
            roleListResponse.setResponseMessage(ResponseEnum.ResponseCode.DB_ERROR.getMessage());
        }
        return roleListResponse;
    }
}

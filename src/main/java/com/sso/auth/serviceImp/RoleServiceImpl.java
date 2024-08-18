package com.sso.auth.serviceImp;

import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.mapper.RoleMapper;
import com.sso.auth.model.Role;
import com.sso.auth.payload.role.RoleDto;
import com.sso.auth.payload.role.RoleList;
import com.sso.auth.payload.role.RoleListResponse;
import com.sso.auth.repository.RoleRepository;
import com.sso.auth.service.RoleService;
import lombok.AllArgsConstructor;
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
        Role role = RoleMapper.mapToRole(request);
        role = roleRepository.save(role);
        roleResponse = RoleMapper.mapToRoleDto(role);
        roleResponse.setCorrelationId(serviceId);
        roleResponse.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
        roleResponse.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
        roleResponse.setTransactionId(request.getTransactionId());
        return roleResponse;
    }

    @Override
    public RoleListResponse roleList(String serviceId) {
        RoleListResponse roleListResponse = new RoleListResponse();
        List<Role> roleList = roleRepository.findAll();
        List<RoleList> roleLists = roleList.stream().map(roleListMap -> RoleMapper.mapToRoleList(roleListMap)).collect(Collectors.toList());
        roleListResponse.setRoleList(roleLists);
        roleListResponse.setCorrelationId(serviceId);
        roleListResponse.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
        roleListResponse.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
        return roleListResponse;
    }
}

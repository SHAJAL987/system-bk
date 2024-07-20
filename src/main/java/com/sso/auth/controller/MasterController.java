package com.sso.auth.controller;

import com.sso.auth.Utilities.CorrelationIdGen;
import com.sso.auth.Utilities.ErrorObject;
import com.sso.auth.payload.Application.ApplicationDto;
import com.sso.auth.payload.Application.ApplicationListResponse;
import com.sso.auth.payload.Role.RoleDto;
import com.sso.auth.payload.Role.RoleListResponse;
import com.sso.auth.payload.User.UserDto;
import com.sso.auth.payload.User.UserListResponse;
import com.sso.auth.payload.UserRole.UserRoleDto;
import com.sso.auth.service.ApplicationService;
import com.sso.auth.service.RoleService;
import com.sso.auth.service.UserRoleService;
import com.sso.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth/v1")
public class MasterController extends BaseAuthController{
    private ApplicationService applicationService;
    private RoleService roleService;
    private UserService userService;
    private UserRoleService userRoleService;

    private final String SERVICE_ID = CorrelationIdGen.getCorrelationId();


    //############################## APPLICATION CONTROLLERS ##############################################
    @PostMapping("/app")
    public ResponseEntity createApplication(@RequestBody ApplicationDto request){
        ResponseEntity response;
        try{
            ApplicationDto objResponse = applicationService.saveApplication(SERVICE_ID,request);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorObject("No data found"));
            return response;
        }catch (Exception ex){
            return handleException(ex);
        }
    }

    @GetMapping("/app")
    public ResponseEntity getAllApplicationList(){
        ResponseEntity response;
        try{
            ApplicationListResponse objResponse = applicationService.getAllMail(SERVICE_ID);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorObject("No data found"));
            return response;
        }catch (Exception ex){
            return handleException(ex);
        }
    }

    //################################## ROLE CONTROLLERS ####################################################
    @PostMapping("/role")
    public ResponseEntity createRole(
            @RequestBody RoleDto request
    ){
        ResponseEntity response;
        try{
            RoleDto objResponse = roleService.saveRole(SERVICE_ID,request);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorObject("No data found"));
            return response;
        }catch (Exception ex){
            return handleException(ex);
        }
    }
    @GetMapping("/role")
    public ResponseEntity getRole(){
        ResponseEntity response;
        try{
            RoleListResponse objResponse = roleService.roleList(SERVICE_ID);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorObject("No data found"));
            return response;
        }catch (Exception ex){
            return handleException(ex);
        }
    }
    //################################## USER CONTROLLERS ####################################################
    @PostMapping("/user")
    public ResponseEntity createUser(
            @RequestBody UserDto request
    ){
        ResponseEntity response;
        try{
            UserDto objResponse = userService.saveUser(SERVICE_ID,request);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorObject("No data found"));
            return response;
        }catch (Exception ex){
            return handleException(ex);
        }
    }
    @GetMapping("/user")
    public ResponseEntity getAllUser(){
        ResponseEntity response;
        try{
            UserListResponse objResponse = userService.getAllUser(SERVICE_ID);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorObject("No data found"));
            return response;
        }catch (Exception ex){
            return handleException(ex);
        }
    }
    //################################## USER ROLE CONTROLLERS ####################################################
    @PostMapping("/user/role")
    public ResponseEntity createUserRole(
            @RequestBody UserRoleDto request
    ){
        ResponseEntity response;
        try{
            UserRoleDto objResponse = userRoleService.saveUserRole(SERVICE_ID,request);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorObject("No data found"));
            return response;
        }catch (Exception ex){
            return handleException(ex);
        }
    }
}

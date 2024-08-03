package com.sso.auth.controller;

import com.sso.auth.Utilities.CorrelationIdGen;
import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.payload.exception.ErrorObject;
import com.sso.auth.exception.ResourceNotFoundException;
import com.sso.auth.payload.application.ApplicationDto;
import com.sso.auth.payload.application.ApplicationListResponse;
import com.sso.auth.payload.exception.ExceptionDetails;
import com.sso.auth.payload.menu.MenuChildDto;
import com.sso.auth.payload.menu.MenuDto;
import com.sso.auth.payload.role.RoleDto;
import com.sso.auth.payload.role.RoleListResponse;
import com.sso.auth.payload.user.UserDto;
import com.sso.auth.payload.user.UserListResponse;
import com.sso.auth.payload.userRole.UserRoleDto;
import com.sso.auth.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/v1")
public class MasterController extends BaseAuthController{
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private MenuService menuService;

//    private final String SERVICE_ID = CorrelationIdGen.getCorrelationId();


    //############################## APPLICATION CONTROLLERS ##############################################
    @PostMapping("/app")
    public ResponseEntity createApplication(@RequestBody ApplicationDto request){
        //logRequest(request);
        ResponseEntity response;
        try{
            ApplicationDto objResponse = applicationService.saveApplication(serviceID,request);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDetails(serviceID, ResponseEnum.ResponseCode.NOT_FOUND.getCode(), ResponseEnum.ResponseCode.NOT_FOUND.getMessage()));
            //logResponse(response.getBody());
            return response;
        }catch (Exception ex){
            return handleException(ex,serviceID);
        }
    }

    @GetMapping("/app")
    public ResponseEntity getAllApplicationList(){
        ResponseEntity response;
        try{
            ApplicationListResponse objResponse = applicationService.getAllMail(serviceID);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDetails(serviceID, ResponseEnum.ResponseCode.NOT_FOUND.getCode(), ResponseEnum.ResponseCode.NOT_FOUND.getMessage()));
            //logResponse(response.getBody());
            return response;
        }catch (Exception ex){
            return handleException(ex,serviceID);
        }
    }

    @PutMapping("/app")
    public ResponseEntity updateApplication(
            @RequestBody ApplicationDto request
    ){
        //logRequest(request);
        ResponseEntity response;
        try{
            ApplicationDto objResponse = applicationService.updateApplication(serviceID,request);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDetails(serviceID, ResponseEnum.ResponseCode.NOT_FOUND.getCode(), ResponseEnum.ResponseCode.NOT_FOUND.getMessage()));
            //logResponse(response.getBody());
            return response;
        }catch (ResourceNotFoundException ex){
            return handleNotFoundException(ex,serviceID);
        }
        catch (Exception ex){
            return handleException(ex,serviceID);
        }
    }



    //################################## ROLE CONTROLLERS ####################################################
    @PostMapping("/role")
    public ResponseEntity createRole(
            @RequestBody RoleDto request
    ){
        //logRequest(request);
        ResponseEntity response;
        try{
            RoleDto objResponse = roleService.saveRole(serviceID,request);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDetails(serviceID, ResponseEnum.ResponseCode.NOT_FOUND.getCode(), ResponseEnum.ResponseCode.NOT_FOUND.getMessage()));
            //logResponse(response.getBody());
            return response;
        }catch (Exception ex){
            return handleException(ex,serviceID);
        }
    }
    @GetMapping("/role")
    public ResponseEntity getRole(){
        ResponseEntity response;
        try{
            RoleListResponse objResponse = roleService.roleList(serviceID);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDetails(serviceID, ResponseEnum.ResponseCode.NOT_FOUND.getCode(), ResponseEnum.ResponseCode.NOT_FOUND.getMessage()));
            //logResponse(response.getBody());
            return response;
        }catch (Exception ex){
            return handleException(ex,serviceID);
        }
    }
    //################################## USER CONTROLLERS ####################################################
    @PostMapping("/user")
    public ResponseEntity createUser(
            @RequestBody UserDto request
    ){
        //logRequest(request);
        ResponseEntity response;
        try{
            UserDto objResponse = userService.saveUser(serviceID,request);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDetails(serviceID, ResponseEnum.ResponseCode.NOT_FOUND.getCode(), ResponseEnum.ResponseCode.NOT_FOUND.getMessage()));
            //logResponse(response.getBody());
            return response;
        }catch (Exception ex){
            return handleException(ex,serviceID);
        }
    }
    @GetMapping("/user")
    public ResponseEntity getAllUser(){
        ResponseEntity response;
        try{
            UserListResponse objResponse = userService.getAllUser(serviceID);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDetails(serviceID, ResponseEnum.ResponseCode.NOT_FOUND.getCode(), ResponseEnum.ResponseCode.NOT_FOUND.getMessage()));
            //logResponse(response.getBody());
            return response;
        }catch (Exception ex){
            return handleException(ex,serviceID);
        }
    }
    //################################## USER ROLE CONTROLLERS ####################################################
    @PostMapping("/user/role")
    public ResponseEntity createUserRole(
            @RequestBody UserRoleDto request
    ){
        //logRequest(request);
        ResponseEntity response;
        try{
            UserRoleDto objResponse = userRoleService.saveUserRole(serviceID,request);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDetails(serviceID, ResponseEnum.ResponseCode.NOT_FOUND.getCode(), ResponseEnum.ResponseCode.NOT_FOUND.getMessage()));
            //logResponse(response.getBody());
            return response;
        }catch (Exception ex){
            return handleException(ex,serviceID);
        }
    }
    //################################## MENU CONTROLLERS ####################################################
    @PostMapping("/menu")
    public ResponseEntity createMenu(
            @RequestBody MenuDto request
    ){
        //logRequest(request);
        ResponseEntity response;
        try{
            MenuDto objResponse = menuService.saveMenu(serviceID,request);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDetails(serviceID, ResponseEnum.ResponseCode.NOT_FOUND.getCode(), ResponseEnum.ResponseCode.NOT_FOUND.getMessage()));
            //logResponse(response.getBody());
            return response;
        }catch (Exception ex){
            return handleException(ex,serviceID);
        }
    }

    @GetMapping("/menu")
    public ResponseEntity getAllMenu(){
        //logRequest(request);
        ResponseEntity response;
        try{
            List<MenuChildDto> objResponse = menuService.getAllMenus();
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDetails(serviceID, ResponseEnum.ResponseCode.NOT_FOUND.getCode(), ResponseEnum.ResponseCode.NOT_FOUND.getMessage()));
            //logResponse(response.getBody());
            return response;
        }catch (Exception ex){
            return handleException(ex,serviceID);
        }
    }


}

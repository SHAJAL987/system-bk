package com.sso.auth.controller;

import com.sso.auth.Utilities.CorrelationIdGen;
import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.exception.ResourceNotFoundException;
import com.sso.auth.payload.application.ApplicationDto;
import com.sso.auth.payload.application.ApplicationListResponse;
import com.sso.auth.payload.exception.ExceptionDetails;
import com.sso.auth.payload.menu.MenuChildDto;
import com.sso.auth.payload.menu.MenuDto;
import com.sso.auth.payload.role.RoleDto;
import com.sso.auth.payload.role.RoleListResponse;
import com.sso.auth.payload.credentials.LoginDto;
import com.sso.auth.payload.credentials.LoginResponse;
import com.sso.auth.payload.user.UserDto;
import com.sso.auth.payload.user.UserListResponse;
import com.sso.auth.payload.userRole.UserRoleDto;
import com.sso.auth.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/v1")
//@Tag(name = "Public API", description = "Operations available for all users")
public class AuthController extends BaseAuthController{
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

    private final String SERVICE_ID = CorrelationIdGen.getCorrelationId();

    // ############################# HEALTH CHECK API ####################################################
    @GetMapping("/health")
    public String healthCheck(HttpServletRequest request){
        return "Auth Health Ok - " + request.getSession().getId();
    }

    // ############################ USER LOGIN ##########################################################
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        ResponseEntity response;
        try{
            LoginResponse objResponse = userService.verify(loginDto);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDetails(serviceID, ResponseEnum.ResponseCode.NOT_FOUND.getCode(), ResponseEnum.ResponseCode.NOT_FOUND.getMessage()));
            //logResponse(response.getBody());
            return response;
        }catch (Exception ex){
            return handleException(ex,serviceID);
        }
        //return userService.verify(loginDto);
    }

    //############################## APPLICATION CONTROLLERS ##############################################
    @PostMapping("/app")
    public ResponseEntity createApplication(@RequestBody ApplicationDto request){
        //logRequest(request);
        ResponseEntity response;
        try{
            ApplicationDto objResponse = applicationService.saveApplication(SERVICE_ID,request);
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
            @Valid @RequestBody RoleDto request
    ){
        //logRequest(request);
        ResponseEntity response;
        try{
            RoleDto objResponse = roleService.saveRole(SERVICE_ID,request);
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
    @PostMapping("/registration")
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
            MenuDto objResponse = menuService.saveMenu(SERVICE_ID,request);
            if (objResponse != null)
                response = ResponseEntity.ok(objResponse);
            else
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDetails(serviceID, ResponseEnum.ResponseCode.NOT_FOUND.getCode(), ResponseEnum.ResponseCode.NOT_FOUND.getMessage()));
            //logResponse(response.getBody());
            return response;
        }catch (Exception ex){
            return handleException(ex,SERVICE_ID);
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

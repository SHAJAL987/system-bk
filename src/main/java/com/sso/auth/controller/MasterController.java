package com.sso.auth.controller;

import com.sso.auth.Utilities.CorrelationIdGen;
import com.sso.auth.Utilities.ErrorObject;
import com.sso.auth.payload.ApplicationDto;
import com.sso.auth.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v1")
public class MasterController extends BaseAuthController{
    private ApplicationService applicationService;

    public MasterController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/saveApp")
    public ResponseEntity createApplication(ApplicationDto request){
        ResponseEntity response;
        try{
            ApplicationDto objResponse = applicationService.saveApplication(CorrelationIdGen.getCorrelationId(),request);
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

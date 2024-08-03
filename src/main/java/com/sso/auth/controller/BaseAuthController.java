package com.sso.auth.controller;

import com.sso.auth.logging.DbLoggerService;
import com.sso.auth.logging.LoggerUtility;
import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.payload.exception.ExceptionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class BaseAuthController extends BaseController{

    @Autowired
    protected DbLoggerService loggerService;
    @Autowired
    protected LoggerUtility loggerUtility;

    protected void logRequest(Object request) {
        setClientOperationDetails();
        loggerService.requestLogger(clientIP, operationName, request, serviceID, false);
    }

    protected void logResponse(Object response) {
        loggerService.responseLogger(clientIP, operationName, response, serviceID, false);
    }
    protected ResponseEntity handleException(Exception ex, String correlationId) {
        ex.printStackTrace();
        ResponseEntity resp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionDetails(correlationId, ResponseEnum.ResponseCode.REQUEST_NOT_SUCCESS.getCode(), ex.getMessage()));
        return resp;
    }
    protected ResponseEntity handleNotFoundException(Exception ex,String correlationId){
        ResponseEntity resp = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDetails(correlationId, ResponseEnum.ResponseCode.NOT_FOUND.getCode(), ex.getMessage()));
        return resp;
    }
}

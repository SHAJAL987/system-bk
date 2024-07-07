package com.sso.auth.controller;

import com.sso.auth.Utilities.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseAuthController {
    protected ResponseEntity handleException(Exception ex) {
        ex.printStackTrace();
        ResponseEntity resp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorObject(ex.getMessage()));
        return resp;
    }
}

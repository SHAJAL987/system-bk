package com.sso.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlreadyExistException extends RuntimeException{
    private String resMessage;
    public AlreadyExistException(String resMessage) {
        super(resMessage);
        this.resMessage = resMessage;
    }
}

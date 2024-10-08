package com.sso.auth.payload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse {
    private Map<String, String> errors;
    private String serviceId;
    private String responseCode;
    private String responseMessage;
    private String transactionId;
    private String timeStamp;
}

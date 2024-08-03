package com.sso.auth.payload.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {
    private int id;
    private Integer appCode;
    private String appName;
    private String appUrl;
    private String appStatus;
    private String correlationId;
    private String responseCode;
    private String responseMessage;
}

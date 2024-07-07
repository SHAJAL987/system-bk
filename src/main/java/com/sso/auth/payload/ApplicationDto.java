package com.sso.auth.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {
    int id;
    Integer appCode;
    String appName;
    String appUrl;
    String appStatus;
    String correlationId;
    String responseCode;
    String responseMessage;
}

package com.sso.auth.logging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DbLoggerDto {
    private int id;
    private String correlationId;
    private String ip;
    private String operationName;
    private String reqResType;
    private String reqResLog;
    private String code;
    private Date timeStamp;
}

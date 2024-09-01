package com.sso.auth.payload.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationList {
    private int id;
    private Integer appCode;
    private String appName;
    private String appUrl;
    private String appDesc;
    private String appStatus;
}

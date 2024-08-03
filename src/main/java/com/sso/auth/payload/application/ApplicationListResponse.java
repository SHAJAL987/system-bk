package com.sso.auth.payload.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationListResponse {
    private List<ApplicationList> applicationList;
    private String correlationId;
    private String responseCode;
    private String responseMessage;
}

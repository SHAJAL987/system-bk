package com.sso.auth.payload.Application;

import com.sso.auth.payload.Application.ApplicationList;
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

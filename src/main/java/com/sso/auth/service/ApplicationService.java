package com.sso.auth.service;

import com.sso.auth.payload.application.ApplicationDto;
import com.sso.auth.payload.application.ApplicationList;
import com.sso.auth.payload.application.ApplicationListResponse;

import java.util.List;
import java.util.Map;

public interface ApplicationService {
    ApplicationDto saveApplication(String correlationId, ApplicationDto request);
    ApplicationListResponse getAllApplication(String correlationId);
    ApplicationDto updateApplication(String correlationId, ApplicationDto request);

    List<ApplicationList> getApplicationsByUserId(int userId);
}

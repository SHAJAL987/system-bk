package com.sso.auth.service;

import com.sso.auth.payload.application.ApplicationDto;
import com.sso.auth.payload.application.ApplicationListResponse;

public interface ApplicationService {
    ApplicationDto saveApplication(String correlationId, ApplicationDto request);
    ApplicationListResponse getAllMail(String correlationId);
    ApplicationDto updateApplication(String correlationId, ApplicationDto request);
}

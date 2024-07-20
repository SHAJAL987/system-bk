package com.sso.auth.service;

import com.sso.auth.payload.Application.ApplicationDto;
import com.sso.auth.payload.Application.ApplicationListResponse;

public interface ApplicationService {
    ApplicationDto saveApplication(String correlationId, ApplicationDto request);
    ApplicationListResponse getAllMail(String correlationId);
}

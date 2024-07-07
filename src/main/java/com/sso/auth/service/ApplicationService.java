package com.sso.auth.service;

import com.sso.auth.payload.ApplicationDto;

public interface ApplicationService {
    ApplicationDto saveApplication(String correlationId, ApplicationDto request);
}

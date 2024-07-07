package com.sso.auth.mapper;

import com.sso.auth.model.Application;
import com.sso.auth.payload.ApplicationDto;

public class ApplicationMapper {
    public static Application mapToApplication(ApplicationDto applicationDto){
        Application application = new Application();
        application.setAppName(applicationDto.getAppName());
        application.setAppUrl(applicationDto.getAppUrl());
        application.setAppStatus(applicationDto.getAppStatus());

        return application;
    }

    public static ApplicationDto mapToApplicationDto(Application application){
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setId(application.getId());
        applicationDto.setAppCode(application.getAppCode());
        applicationDto.setAppName(application.getAppName());
        applicationDto.setAppUrl(application.getAppUrl());
        applicationDto.setAppStatus(applicationDto.getAppStatus());

        return applicationDto;
    }
}

package com.sso.auth.mapper;

import com.sso.auth.model.Application;
import com.sso.auth.payload.application.ApplicationDto;
import com.sso.auth.payload.application.ApplicationList;

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
        applicationDto.setAppStatus(application.getAppStatus());

        return applicationDto;
    }

    public static ApplicationList mapToApplicationList(Application application){
        String status;
        if (application.getAppStatus().equals("Y")){
            status = "Active";
        }else {
            status = "Inactive";
        }
        ApplicationList applicationList = new ApplicationList();
        applicationList.setId(application.getId());
        applicationList.setAppCode(application.getAppCode());
        applicationList.setAppUrl(application.getAppUrl());
        applicationList.setAppName(application.getAppName());
        applicationList.setAppStatus(status);
        return applicationList;
    }
}

package com.sso.auth.serviceImp;

import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.exception.ResourceNotFoundException;
import com.sso.auth.mapper.ApplicationMapper;
import com.sso.auth.model.Application;
import com.sso.auth.payload.application.ApplicationDto;
import com.sso.auth.payload.application.ApplicationList;
import com.sso.auth.payload.application.ApplicationListResponse;
import com.sso.auth.repository.ApplicationRepository;
import com.sso.auth.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private ApplicationRepository applicationRepository;

    @Override
    public ApplicationDto saveApplication(String correlationId, ApplicationDto request) {
        ApplicationDto applicationDto = null;
        Application application = ApplicationMapper.mapToApplication(request);
        application.setAppCode(generateUniqueAppCode());
        Application saveApplication = applicationRepository.save(application);
        applicationDto = ApplicationMapper.mapToApplicationDto(saveApplication);
        applicationDto.setCorrelationId(correlationId);
        applicationDto.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
        applicationDto.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
        return applicationDto;
    }

    @Override
    public ApplicationListResponse getAllApplication(String correlationId) {
        ApplicationListResponse applicationListResponse = new ApplicationListResponse();
        List<Application> applicationList = applicationRepository.findAll();
        applicationListResponse.setCorrelationId(correlationId);
        applicationListResponse.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
        applicationListResponse.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
        List<ApplicationList> applicationLists = applicationList.stream().map(mapApplicationList -> ApplicationMapper.mapToApplicationList(mapApplicationList)).collect(Collectors.toList());
        applicationListResponse.setApplicationList(applicationLists);
        return applicationListResponse;
    }

    @Override
    public ApplicationDto updateApplication(String correlationId, ApplicationDto request) {
        ApplicationDto response = new ApplicationDto();
        Application applicationById = applicationRepository.findById(request.getId()).orElseThrow(

                () -> new ResourceNotFoundException("Application Id", "Id", String.valueOf(request.getId()))
        );
        applicationById.setId(response.getId());
        applicationById.setAppName(response.getAppName());
        applicationById.setAppUrl(response.getAppUrl());
        applicationById.setAppStatus(response.getAppStatus());
        applicationById.setAppCode(response.getAppCode());

        applicationById = applicationRepository.save(applicationById);
        response = ApplicationMapper.mapToApplicationDto(applicationById);
        response.setCorrelationId(correlationId);
        response.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
        response.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
        return response;
    }

    private Integer generateUniqueAppCode() {
        Random random = new Random();
        Integer appCode;
        Optional<Application> existingApplication;
        do {
            appCode = 1000 + random.nextInt(9000); // Generate a 4-digit number between 1000 and 9999
            existingApplication = applicationRepository.findByAppCode(appCode);
        } while (existingApplication.isPresent());
        return appCode;
    }
}

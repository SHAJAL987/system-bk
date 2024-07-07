package com.sso.auth.serviceImp;

import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.mapper.ApplicationMapper;
import com.sso.auth.model.Application;
import com.sso.auth.payload.ApplicationDto;
import com.sso.auth.repository.ApplicationRepository;
import com.sso.auth.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public ApplicationDto saveApplication(String correlationId, ApplicationDto request) {
        ApplicationDto applicationDto = null;
        try {
            Application application = ApplicationMapper.mapToApplication(request);
            application.setAppCode(generateUniqueAppCode());
            Application saveApplication = applicationRepository.save(application);
            applicationDto = ApplicationMapper.mapToApplicationDto(saveApplication);
            applicationDto.setCorrelationId(correlationId);
            applicationDto.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
            applicationDto.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
        } catch (Exception ex) {
            applicationDto.setCorrelationId(correlationId);
            applicationDto.setResponseCode(ResponseEnum.ResponseCode.REQUEST_NOT_SUCCESS.getCode());
            applicationDto.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_NOT_SUCCESS.getMessage());
        }
        return applicationDto;
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

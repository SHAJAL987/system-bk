package com.sso.auth.serviceImp;

import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.mapper.ApplicationMapper;
import com.sso.auth.model.Application;
import com.sso.auth.payload.Application.ApplicationDto;
import com.sso.auth.payload.Application.ApplicationList;
import com.sso.auth.payload.Application.ApplicationListResponse;
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
            applicationDto.setResponseCode(ResponseEnum.ResponseCode.DB_ERROR.getCode());
            applicationDto.setResponseMessage(ResponseEnum.ResponseCode.DB_ERROR.getMessage());
        }
        return applicationDto;
    }

    @Override
    public ApplicationListResponse getAllMail(String correlationId) {
        ApplicationListResponse applicationListResponse = new ApplicationListResponse();
        try{
            List<Application> applicationList = applicationRepository.findAll();
            applicationListResponse.setCorrelationId(correlationId);
            applicationListResponse.setResponseCode(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getCode());
            applicationListResponse.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_SUCCESS.getMessage());
            List<ApplicationList> applicationLists = applicationList.stream().map(mapApplicationList ->ApplicationMapper.mapToApplicationList(mapApplicationList)).collect(Collectors.toList());
            applicationListResponse.setApplicationList(applicationLists);
        }catch (Exception ex){
            applicationListResponse.setCorrelationId(correlationId);
            applicationListResponse.setResponseCode(ResponseEnum.ResponseCode.DB_ERROR.getCode());
            applicationListResponse.setResponseMessage(ResponseEnum.ResponseCode.DB_ERROR.getMessage()+" - "+ex.getMessage());
        }
        return applicationListResponse;
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

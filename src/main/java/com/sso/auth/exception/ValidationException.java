package com.sso.auth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sso.auth.Utilities.ResponseEnum;
import com.sso.auth.model.ValidationResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationException{

    private SimpleDateFormat ft3 = new SimpleDateFormat( "yyDDDHHmm"); //"ddmmss");
    private SimpleDateFormat ft4 = new SimpleDateFormat("yyyyMMddHHmmss");
    private int sequenceNumber;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String concatenatedTransactionId = extractAndConcatenateTransactionId((CachedBodyHttpServletRequest) request);

        ValidationResponse response = new ValidationResponse();
        response.setServiceId(serviceID());
        response.setResponseCode(ResponseEnum.ResponseCode.REQUEST_ERROR.getCode());
        response.setResponseMessage(ResponseEnum.ResponseCode.REQUEST_ERROR.getMessage());
        response.setErrors(errors);
        response.setChannelTransactionId(concatenatedTransactionId);  // Set as needed
        response.setTimeStamp(getTimeStamp());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String serviceID(){
        Date myDate = new Date();
        //int myRandom = (int) (Math.random() * 8847);
        String sequenceString = "0000" + String.valueOf(sequenceNumber);
        if(sequenceNumber < 1000)
            sequenceNumber++;
        else
            sequenceNumber = 0;
        String serviceID = ft3.format(myDate).toString() + sequenceString.substring(sequenceString.length() - 3, sequenceString.length()); //+ (new Random().nextInt(10)); //+ myRandom;
        return serviceID;
    }

    private String getTimeStamp() {
        Date myDate = new Date();
        return ft4.format(myDate).toString();
    }

    private String extractAndConcatenateTransactionId(CachedBodyHttpServletRequest request) {
        try {
            String requestBody = request.getCachedBody();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> requestBodyMap = objectMapper.readValue(requestBody, Map.class);
            String channelName = (String) requestBodyMap.get("channelName");
            String channelTransactionId = (String) requestBodyMap.get("channelTransactionId");
            return channelName + "-" + channelTransactionId;
        } catch (Exception e) {
            return "Unknown-Unknown"; // Default value if extraction fails
        }
    }
}

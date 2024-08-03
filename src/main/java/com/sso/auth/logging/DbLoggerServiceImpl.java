package com.sso.auth.logging;

import com.sso.auth.Utilities.Masking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Service
public class DbLoggerServiceImpl implements DbLoggerService {
    private DbLoggerRepository dbLoggerRepository;
    private LoggerUtility loggerUtility;

    @Override
    public void requestLogger(String clientIp, String operationName, Object myObject, String serviceId, boolean isError) {
        String tString = "";
        Object obj = new Object();
        tString = "{ ";

        for (Field f : myObject.getClass().getDeclaredFields()) {
            tString = tString + maskField(myObject, obj, f, false) + ", ";
        }
        for (Field f : myObject.getClass().getSuperclass().getDeclaredFields()) {
            tString = tString + maskField(myObject, obj, f, true) + ", ";
        }

        tString = tString.substring(0, tString.length() - 2);
        tString = tString + " }";

        SimpleDateFormat ft4 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date myDate = new Date();
        DbLoggerDto dbLoggerDto = new DbLoggerDto();

        try {
            dbLoggerDto.setTimeStamp(myDate);
            dbLoggerDto.setIp(clientIp);
            dbLoggerDto.setCode("0");
            dbLoggerDto.setCorrelationId(serviceId);
            dbLoggerDto.setReqResType("REQUEST");
            dbLoggerDto.setReqResLog(tString);
            dbLoggerDto.setOperationName(operationName);
            dbLoggerDto = saveDbLog(serviceId,dbLoggerDto);
        }catch (Exception ex){
            loggerUtility.logError("Correlation Id - " + serviceId + " - Error Closing DB Connection - "+ex.getMessage());
        }

        if (isError){
            loggerUtility.logError("Correlation Id -  " + serviceId + " - Operation Name - " + operationName + " - Client IP - " + clientIp + " - Error - " + tString);
        }else {
            loggerUtility.logInfo("Correlation Id -  " + serviceId + " - Operation Name - " + operationName + " - Client IP - " + clientIp + " - Request - " + tString);
        }
    }

    @Override
    public void responseLogger(String clientIp, String operationName, Object myObject, String serviceId, boolean isError) {
        String tString = "";
        Object obj = new Object();
        tString = "{ ";

        for (Field f : myObject.getClass().getDeclaredFields()) {
            tString = tString + maskField(myObject, obj, f, false) + ", ";
        }
        for (Field f : myObject.getClass().getSuperclass().getDeclaredFields()) {
            tString = tString + maskField(myObject, obj, f, true) + ", ";
        }

        tString = tString.substring(0, tString.length() - 2);
        tString = tString + " }";

        SimpleDateFormat ft4 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date myDate = new Date();
        DbLoggerDto dbLoggerDto = new DbLoggerDto();

        try {
            dbLoggerDto.setTimeStamp(myDate);
            dbLoggerDto.setIp(clientIp);
            dbLoggerDto.setCode("100");
            dbLoggerDto.setCorrelationId(serviceId);
            dbLoggerDto.setReqResType("RESPONSE");
            dbLoggerDto.setReqResLog(tString);
            dbLoggerDto.setOperationName(operationName);
            dbLoggerDto = saveDbLog(serviceId,dbLoggerDto);
        }catch (Exception ex){
            loggerUtility.logError("Correlation Id - " + serviceId + " - Error Closing DB Connection - "+ex.getMessage());
        }

        if (isError){
            loggerUtility.logError("Correlation Id -  " + serviceId + " - Operation Name - " + operationName + " - Client IP - " + clientIp + " - Error - " + tString);
        }else {
            loggerUtility.logInfo("Correlation Id -  " + serviceId + " - Operation Name - " + operationName + " - Client IP - " + clientIp + " - Response - " + tString);
        }
    }

    private DbLoggerDto saveDbLog(String serviceId, DbLoggerDto request) {
        DbLoggerDto response = new DbLoggerDto();
        try{
            DbLogger dbLogger = DbLoggerMapper.mapToDbLogger(request);
            dbLogger = dbLoggerRepository.save(dbLogger);
            response = DbLoggerMapper.mapToDbLoggerDto(dbLogger);
        }catch (Exception ex){
            loggerUtility.logError("Correlation Id - " + serviceId + " - SQL/DB connectivity Error - "+ex.getMessage());
        }
        return response;
    }

    private String maskField(Object myObject, Object obj, Field f, boolean superClass){
        String tString = "";
        String password = "";
        String PIN = "";
        String smsText = "";
        f.setAccessible(true);
        try {
            if (f.getName().trim().equals("password")) {
                if(f.get(myObject) != null) {
                    password = f.get(myObject).toString().trim();
                    password = this.maskPassword(password);
                    tString = tString + f.getName() + ":" + password + "  ";
                }
            } else if (f.getName().trim().equals("newPIN")) {
                if(f.get(myObject) != null) {
                    PIN = f.get(myObject).toString().trim();
                    PIN = this.maskPassword(PIN);
                    tString = tString + "******" + ":" + PIN + "*****  ";
                }
            }  else if (f.getName().trim().equals("oldPIN")) {
                if(f.get(myObject) != null) {
                    PIN = f.get(myObject).toString().trim();
                    PIN = this.maskPassword(PIN);
                    tString = tString + "******" + ":" + PIN + "*****,  ";
                }
            } else if (f.getName().trim().equals("message()")) {
                if(f.get(myObject) != null) {
                    smsText = f.get(myObject).toString().trim();
                    smsText = smsText.replaceAll("\\d", "");
                    tString = tString + "******" + ":" + smsText + "*****  ";
                }
            } else {
                tString = tString + f.getName() + ":" + f.get(myObject) + "  ";
            }

            return tString;
        } catch (IllegalArgumentException | IllegalAccessException e) {
            loggerUtility.logError("Request log db error: " + e.getMessage());
            return "";
        }
    }
    private String maskPassword(String password) {
        return Masking.maskNumber(password, 0, password.length(), '*');
    }

}

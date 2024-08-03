package com.sso.auth.controller;

import com.sso.auth.logging.LoggerUtility;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseController {
    @Autowired
    protected LoggerUtility loggerUtility;
    private HttpServletRequest httpServletRequest;
    protected String clientIP;
    protected String operationName;
    protected String serviceID;
    protected String timeStamp;

    private SimpleDateFormat ft3 = new SimpleDateFormat( "yyDDDHHmm"); //"ddmmss");
    private SimpleDateFormat ft4 = new SimpleDateFormat("yyyyMMddHHmmss");

    private static int sequenceNumber;

    public void setRequest(HttpServletRequest request) {
        this.httpServletRequest = request;
    }
    protected String getServiceID() {
        Date myDate = new Date();
        String sequenceString = "0000" + String.valueOf(sequenceNumber);
        if(sequenceNumber < 1000)
            sequenceNumber++;
        else
            sequenceNumber = 0;
        String serviceID = ft3.format(myDate).toString() + sequenceString.substring(sequenceString.length() - 3, sequenceString.length()); //+ (new Random().nextInt(10)); //+ myRandom;
        return serviceID;

    }
    protected String getTimeStamp() {
        Date myDate = new Date();
        return ft4.format(myDate).toString();
    }
    private String getClientIP() {
        String remoteAddr = "";
        if (httpServletRequest != null) {
            remoteAddr = httpServletRequest.getRemoteAddr();
        }

        return remoteAddr;
    }
    private String getOperationName() {
        String operationName = "";
        try {
            operationName = httpServletRequest.getRequestURI() + "/" + httpServletRequest.getMethod();
        } catch (Exception ex) {
            operationName = "";
        }
        return operationName;
    }
    protected void setClientOperationDetails() {
        clientIP = getClientIP();
        operationName = getOperationName();
        serviceID = getServiceID();
        timeStamp = getTimeStamp();
    }
}

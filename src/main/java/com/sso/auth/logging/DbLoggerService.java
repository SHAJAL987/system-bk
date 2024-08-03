package com.sso.auth.logging;

import com.sso.auth.logging.DbLoggerDto;

public interface DbLoggerService {
    void requestLogger(String clientIp, String operationName, Object myObject, String serviceId, boolean isError);
    void responseLogger(String clientIp, String operationName, Object myObject, String serviceId, boolean isError);
}

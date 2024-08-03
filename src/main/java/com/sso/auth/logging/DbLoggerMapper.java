package com.sso.auth.logging;

public class DbLoggerMapper {
    public static DbLogger mapToDbLogger(DbLoggerDto request){
        DbLogger dbLogger = new DbLogger();
        dbLogger.setIp(request.getIp());
        dbLogger.setTimeStamp(request.getTimeStamp());
        dbLogger.setCorrelationId(request.getCorrelationId());
        dbLogger.setOperationName(request.getOperationName());
        dbLogger.setReqResType(request.getReqResType());
        dbLogger.setReqResLog(request.getReqResLog());
        dbLogger.setCode(request.getCode());

        return dbLogger;
    }

    public static DbLoggerDto mapToDbLoggerDto (DbLogger request){
        DbLoggerDto dbLoggerDto = new DbLoggerDto();
        dbLoggerDto.setId(request.getId());
        dbLoggerDto.setIp(request.getIp());
        dbLoggerDto.setOperationName(request.getOperationName());
        dbLoggerDto.setCode(request.getCode());
        dbLoggerDto.setCorrelationId(request.getCorrelationId());
        dbLoggerDto.setReqResType(request.getReqResType());
        dbLoggerDto.setReqResLog(request.getReqResLog());
        dbLoggerDto.setTimeStamp(request.getTimeStamp());
        return dbLoggerDto;
    }
}

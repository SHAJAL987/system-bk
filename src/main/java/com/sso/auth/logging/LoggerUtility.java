package com.sso.auth.logging;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LoggerUtility {
    private AppLogger appLogger;
    @Value("${app.general.logger.logPath}")
    private String logFileDir;

//    public LoggerUtility(AppLogger appLogger) {
//        this.appLogger = appLogger;
//    }

    public void logError(String errorDetails) {
        appLogger.error(errorDetails);
    }
    public void logInfo(String errorDetails) {
        appLogger.info(errorDetails);
    }

    public void logging(String serviceName, String serviceId, String publishText, Object myObject, boolean isError) {
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = myObject.toString();

        try {
            requestJson = mapper.writeValueAsString(myObject);

        } catch (JsonGenerationException e) {
            appLogger.error("correlation Id -  " + serviceId + " - Source - " + publishText + " - " + " >> Error Request writing log: " + e.getMessage().substring(200));

        } catch (JsonMappingException e) {
            appLogger.error("correlation Id -  " + serviceId + " - Source - " + publishText + " - " + " >> Error Request writing log: " + e.getMessage().substring(200));

        } catch (IOException e) {
            appLogger.error("correlation Id -  " + serviceId + " - Source - " + publishText + " - " + " >> Error Request writing log: " + e.getMessage().substring(200));
        }
        writeLog(serviceName , "correlation Id -  " + serviceId + " - Source - " + publishText + " - " + requestJson, isError);
    }

    public void writeLog(String serviceName, String publishText, boolean isError) {
        String fileName = serviceName;
        String errorFileName = "error-"+serviceName;
        if (isError) {
            writeLogs(publishText, errorFileName);
        } else {
            writeLogs(publishText, fileName);
        }
    }

    private void writeLogs(String message, String fileName) {
        try {
            Date myDate = new Date();
            SimpleDateFormat ft4 = new SimpleDateFormat("dd-MM-yyyy");

            BufferedWriter writer = new BufferedWriter(new FileWriter(logFileDir +  fileName + "-" + ft4.format(myDate) + ".log", true));
            writer.write(message);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

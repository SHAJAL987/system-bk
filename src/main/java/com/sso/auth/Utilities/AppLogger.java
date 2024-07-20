package com.sso.auth.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {
    private final Logger traceLogger;
    private final Logger errorLogger;
    public AppLogger(String traceLoggerName, String errorLoggerName) {
        this.traceLogger = LoggerFactory.getLogger(traceLoggerName);
        this.errorLogger = LoggerFactory.getLogger(errorLoggerName);
    }
    public void error(Exception exp) {
        errorLogger.error(exp.toString());
    }
    public void error(String message) {
        errorLogger.error(message);
    }
    public void error(String message, Exception exp) {
        errorLogger.error(message, exp);
    }
    public void info(String message) {
        traceLogger.info(message);
    }
    public void trace(String message) {
        traceLogger.trace(message);
    }
}

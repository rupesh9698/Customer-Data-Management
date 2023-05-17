package com.dm.customer.util;

import jakarta.inject.Singleton;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

@Singleton
public class CDMLogger {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CDMLogger.class);

    /**
     * This method is used for showing the logs
     */
    public void logs(String className, String methodName, String message) {
        Map<String, String> loggerMap = createLogEntries(className, methodName);
        logger.info(message, StructuredArguments.entries(loggerMap));
    }

    /**
     * This method is used for returning the logMap in logs method
     */
    private Map<String, String> createLogEntries(String className, String methodName) {
        final Map<String, String> logMap = new LinkedHashMap<>();
        logMap.put("className", className);
        logMap.put("methodName", methodName);
        return logMap;
    }
}

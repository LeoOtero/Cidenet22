package com.cidenet.project.util;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Logger {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger("Cidenet");

    public void logMessage(LoggingLevels loggingLevel, String message) { log(loggingLevel, message); }

    private void log(LoggingLevels level, String message) {
        switch (level) {
            case ERROR:
                this.logger.error(message);
                return;
            case INFO:
                this.logger.info(message);
                return;
            case DEBUG:
                this.logger.debug(message);
                return;
            case TRACE:
                this.logger.trace(message);
                return;
        }
        this.logger.warn(message);
    }

}

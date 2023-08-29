package com.company.app.core.aop.logging.util;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;

/**
 * Методы для логирования.
 *
 * @author shibaev.aleksey 27.12.2022
 */
@UtilityClass
public final class Logs {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Logs.class);

    public static void doExceptionLog(Logger log, Exception e, String message) {
        log.error(message, e.getMessage(), e);
    }

    public static void doExceptionLog(Logger log, Exception e) {
        log.error(e.getMessage(), e);
    }

    public static void doExceptionLog(Throwable e, String message) {
        log.error(message, e.getMessage(), e);
    }

    public static void doExceptionLog(Exception e, String message) {
        log.error(message, e.getMessage(), e);
    }

    public static void doExceptionLog(Throwable e) {
        log.error(e.getMessage(), e);
    }

}

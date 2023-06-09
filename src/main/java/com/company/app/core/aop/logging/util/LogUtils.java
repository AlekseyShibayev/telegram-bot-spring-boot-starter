package com.company.app.core.aop.logging.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Методы для логирования.
 *
 * @author shibaev.aleksey 27.12.2022
 */
@Slf4j
@UtilityClass
public class LogUtils {

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

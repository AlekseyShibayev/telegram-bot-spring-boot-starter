package com.company.app.core.util;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;

import java.util.Collection;

@UtilityClass
public class Logs {

    public static void doExceptionLog(Logger log, Exception e, String message) {
        log.error(message, e.getMessage(), e);
    }

    public static void doExceptionLog(Logger log, Exception e) {
        log.error(e.getMessage(), e);
    }

    public static void doStartLog(Logger log, String logContext) {
        if (log.isDebugEnabled()) {
            log.debug("[{}]: start", logContext);
        }
    }

    public static void doEndLog(Logger log, String logContext) {
        if (log.isDebugEnabled()) {
            log.debug("[{}]: end", logContext);
        }
    }

    public static void doEndLog(Logger log, String logContext, Collection<?> result) {
        if (log.isDebugEnabled()) {
            log.debug("[{}]: end, with result size [{}]", logContext, result.size());
        }
    }

}

package com.company.app.core.aop.logging.performance.component.impl;

import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import com.company.app.core.aop.logging.performance.component.action.PerformanceLogAction;
import com.company.app.core.aop.logging.performance.component.api.PerformanceLogActionRegistry;
import com.company.app.core.aop.logging.performance.component.api.PerformanceLogGuidExtractor;
import com.company.app.core.aop.logging.performance.component.api.PerformanceLogReflector;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author shibaev.aleksey 04.04.2023
 */
@Slf4j
@Component
public class PerformanceLogGuidExtractorImpl implements PerformanceLogGuidExtractor {

    @Autowired
    PerformanceLogReflector reflector;
    @Autowired
    PerformanceLogActionRegistry actionRegistry;

    public String extractGuid(ProceedingJoinPoint proceedingJoinPoint) {
        String result = "";
        Stopwatch stopwatch = Stopwatch.createStarted();

        try {
            PerformanceLogAnnotation annotation = reflector.getAnnotation(proceedingJoinPoint, PerformanceLogAnnotation.class);
            PerformanceLogAction action = actionRegistry.getAction(annotation.actionType());
            result = action.getGuid(proceedingJoinPoint, annotation);
        } catch (Exception e) {
            log.trace(e.getMessage(), e);
            result = UUID.randomUUID().toString();
        } finally {
            stopwatch.stop();
            log.debug("[{}] выковыривание guid заняло [{}] ms.", result, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
        return result;
    }
}

package com.company.app.core.aop.logging.performance.component;

import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import com.company.app.core.aop.logging.performance.component.action.PerformanceLogAction;
import com.google.common.base.Stopwatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class PerformanceLogGuidExtractor {

    private final PerformanceLogReflector reflector;
    private final PerformanceLogActionRegistry actionRegistry;

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

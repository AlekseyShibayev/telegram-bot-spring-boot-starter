package com.company.app.core.aop.logging.performance.component.action;

import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import com.company.app.core.aop.logging.performance.component.config.PerformanceLogActionType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PerformanceLogRandomAction extends PerformanceLogAbstractAction {

    @Override
    public PerformanceLogActionType getType() {
        return PerformanceLogActionType.RANDOM;
    }

    @Override
    public String getGuid(ProceedingJoinPoint proceedingJoinPoint, PerformanceLogAnnotation annotation) {
        return UUID.randomUUID().toString();
    }
}

package com.company.app.core.aop.logging.performance.component.action;

import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import com.company.app.core.aop.logging.performance.component.PerformanceLogActionType;
import org.aspectj.lang.ProceedingJoinPoint;

public interface PerformanceLogAction {

    PerformanceLogActionType getType();

    String getGuid(ProceedingJoinPoint proceedingJoinPoint, PerformanceLogAnnotation annotation);

}

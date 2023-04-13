package com.company.app.core.aop.logging.performance.component.api;

import org.aspectj.lang.ProceedingJoinPoint;

public interface PerformanceLogGuidExtractor {

	String extractGuid(ProceedingJoinPoint proceedingJoinPoint);
}

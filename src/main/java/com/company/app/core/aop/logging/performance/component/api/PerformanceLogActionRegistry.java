package com.company.app.core.aop.logging.performance.component.api;

import com.company.app.core.aop.logging.performance.component.config.PerformanceLogActionType;
import com.company.app.core.aop.logging.performance.component.action.PerformanceLogAction;

public interface PerformanceLogActionRegistry {

	PerformanceLogAction getAction(PerformanceLogActionType actionType);
}

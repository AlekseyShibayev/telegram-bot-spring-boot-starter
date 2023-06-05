package com.company.app.core.aop.logging.performance.component.api;

import com.company.app.core.aop.logging.performance.component.action.PerformanceLogAction;
import com.company.app.core.aop.logging.performance.component.config.PerformanceLogActionType;

public interface PerformanceLogActionRegistry {

    PerformanceLogAction getAction(PerformanceLogActionType actionType);
}

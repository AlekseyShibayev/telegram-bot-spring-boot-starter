package com.company.app.core.aop.logging.performance.component.action;

import com.company.app.core.aop.logging.performance.component.api.PerformanceLogReflector;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PerformanceLogAbstractAction implements PerformanceLogAction {

    @Autowired
    PerformanceLogReflector reflector;
}

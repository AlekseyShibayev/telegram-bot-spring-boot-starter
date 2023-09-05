package com.company.app.core.aop.logging.performance;

import com.company.app.core.aop.logging.performance.component.PerformanceLogActionType;
import com.company.app.core.aop.logging.performance.testEntity.Context;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestComponent;

/**
 * 4 примера использования  {@link PerformanceLogAnnotation}
 * Можно запустить тесты из {@link PerformanceLogAspectTest}
 */
@Slf4j
@TestComponent
public class PerformanceLogAspectExample {

    @PerformanceLogAnnotation
    public void anyMethodNameWithEmptyAnnotation(Context context) {
        log.debug("1. anyMethodNameWithEmptyAnnotation");
    }

    @PerformanceLogAnnotation(actionType = PerformanceLogActionType.NUMBER, number = "1")
    public void anyMethodNameWithGuidAsParameter(Context context, String guid) {
        log.debug("2. anyMethodNameWithGuidAsParameter");
    }

    @PerformanceLogAnnotation(actionType = PerformanceLogActionType.NUMBER_AND_METHOD, number = "0", methodName = "getGuidMethod")
    public void anyMethodNameWithNumberAndMethodName(Context context) {
        log.debug("3. anyMethodNameWithNumberAndMethodName");
    }

    @PerformanceLogAnnotation(actionType = PerformanceLogActionType.NUMBER_AND_FIELD, number = "0", fieldName = "guid")
    public void anyMethodNameWithNumberAndFieldName(Context context) {
        log.debug("4. anyMethodNameWithNumberAndFieldName");
    }
}

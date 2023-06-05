package com.company.app.core.aop.logging.performance.component.action;

import com.company.app.core.aop.logging.performance.PerformanceLogAnnotation;
import com.company.app.core.aop.logging.performance.component.config.PerformanceLogActionType;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

@Component
public class PerformanceLogNumberAndMethodAction extends PerformanceLogAbstractAction {

    @Override
    public PerformanceLogActionType getType() {
        return PerformanceLogActionType.NUMBER_AND_METHOD;
    }

    @SneakyThrows
    @Override
    public String getGuid(ProceedingJoinPoint proceedingJoinPoint, PerformanceLogAnnotation annotation) {
        String methodName = annotation.methodName();
        Object originalObjectFromSignature = reflector.getArg(proceedingJoinPoint, annotation.number());

        Method method = reflector.recursiveMethodSearch(originalObjectFromSignature.getClass(), methodName);
        method.trySetAccessible();
        Object value = method.invoke(originalObjectFromSignature);

        UUID uuid = UUID.fromString(String.valueOf(value));
        return uuid.toString();
    }
}

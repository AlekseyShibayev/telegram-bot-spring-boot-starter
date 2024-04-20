package com.company.app.infrastructure.log.performance.aop;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import com.company.app.infrastructure.log.performance.PerformanceLogConfiguration;
import com.company.app.infrastructure.log.performance.interceptor.HibernateQueryInfoInterceptor;
import com.company.app.infrastructure.log.performance.interceptor.model.QueryInfo;

/**
 * {@link PerformanceLogConfiguration}
 */
@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnBean(PerformanceLogConfiguration.class)
public class PerformanceLogAspect implements MethodInterceptor {

    private final HibernateQueryInfoInterceptor hibernateQueryInfoInterceptor;
    private final PerformanceLogService performanceLogService;

    @Override
    public Object invoke(@NotNull MethodInvocation invocation) throws Throwable {
        if (log.isDebugEnabled()) {
            String key = hibernateQueryInfoInterceptor.createKey(invocation);
            Stopwatch stopwatch = Stopwatch.createStarted();
            return doOriginalMethodWithAdditionalLogging(invocation, key, stopwatch);
        } else {
            return invocation.proceed();
        }
    }

    @Nullable
    private Object doOriginalMethodWithAdditionalLogging(@NotNull MethodInvocation invocation, String key, Stopwatch stopwatch) throws Throwable {
        try {
            Object result = invocation.proceed();

            stopwatch.stop();
            QueryInfo queryInfo = hibernateQueryInfoInterceptor.getQueryInfo(key);

            writeToLog(stopwatch, key, result, queryInfo);
            return result;
        } finally {
            hibernateQueryInfoInterceptor.remove(key);
        }
    }

    private void writeToLog(Stopwatch stopwatch, String classAndMethodName, Object result, QueryInfo queryInfo) {
        long methodExecutionTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);

        if (performanceLogService.isMethodExecutionTimeLessThenExecutionTimeBorder(methodExecutionTime)) {
            return;
        }

        if (result instanceof Collection<?> collection) {
            log.debug("method execution time: [{}] ms, return collection, size: [{}], source: [{}], query info: \n{}",
                    methodExecutionTime,
                    collection.size(),
                    classAndMethodName,
                    queryInfo
            );
        } else if (result instanceof Slice<?> slice) {
            log.debug("method execution time: [{}] ms, return slice, size: [{}], source: [{}], query info: \n{}",
                    methodExecutionTime,
                    slice.getContent().size(),
                    classAndMethodName,
                    queryInfo
            );
        } else {
            log.debug("method execution time: [{}] ms, source: [{}], query info: \n{}",
                    methodExecutionTime,
                    classAndMethodName,
                    queryInfo
            );
        }
    }

}
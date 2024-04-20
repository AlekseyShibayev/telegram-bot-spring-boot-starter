package com.company.app.infrastructure.log.performance.aop;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import com.company.app.infrastructure.log.performance.PerformanceLogConfiguration;

/**
 * For limit log by method execution time use:
 * log.performance.do-not-write-to-log-methods-whose-execution-time-is-less-than-X-ms
 * default - 0 ms
 * <p>
 * {@link PerformanceLogConfiguration}
 */
@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnBean(PerformanceLogConfiguration.class)
public class PerformanceLogService {

    @Setter
    @Value("${log.performance.do-not-write-to-log-methods-whose-execution-time-is-less-than-X-ms:0}")
    private Integer executionTimeBorder;

    public boolean isMethodExecutionTimeLessThenExecutionTimeBorder(long methodExecutionTime) {
        return methodExecutionTime <= executionTimeBorder;
    }

}
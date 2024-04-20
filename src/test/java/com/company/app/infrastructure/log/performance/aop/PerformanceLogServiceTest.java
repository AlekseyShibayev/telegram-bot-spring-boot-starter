package com.company.app.infrastructure.log.performance.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PerformanceLogServiceTest {

    @Test
    void test_1() {
        PerformanceLogService performanceLogFilter = new PerformanceLogService();
        performanceLogFilter.setExecutionTimeBorder(0);
        Assertions.assertTrue(performanceLogFilter.isMethodExecutionTimeLessThenExecutionTimeBorder(0L));
    }

    @Test
    void test_2() {
        PerformanceLogService performanceLogFilter = new PerformanceLogService();
        performanceLogFilter.setExecutionTimeBorder(5_000);
        Assertions.assertTrue(performanceLogFilter.isMethodExecutionTimeLessThenExecutionTimeBorder(100L));
    }

    @Test
    void test_3() {
        PerformanceLogService performanceLogFilter = new PerformanceLogService();
        performanceLogFilter.setExecutionTimeBorder(5_000);
        Assertions.assertFalse(performanceLogFilter.isMethodExecutionTimeLessThenExecutionTimeBorder(10_000L));
    }

}
package com.company.app.infrastructure.log.performance.interceptor.model;

import com.company.app.infrastructure.log.performance.PerformanceLogConfiguration;

/**
 * Log with hibernate call statistics.
 * {@link PerformanceLogConfiguration}
 */
public class QueryInfo {

    private final PreparedSQLCallsStatistic preparedSQLCallsStatistic = new PreparedSQLCallsStatistic();
    private final EntityStorageOnLoadStatistic entityStorageOnLoadStatistic = new EntityStorageOnLoadStatistic();

    public void changeOnPrepareStatement(String sql) {
        preparedSQLCallsStatistic.changeOnPrepareStatement(sql);
    }

    public void changeOnLoad(Object entity) {
        entityStorageOnLoadStatistic.changeOnLoad(entity);
    }

    @Override
    public String toString() {
        return ("onPrepareStatement: %s\nonLoad: %s")
                .formatted(preparedSQLCallsStatistic, entityStorageOnLoadStatistic);
    }

}
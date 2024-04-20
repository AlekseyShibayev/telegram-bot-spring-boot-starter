package com.company.app.infrastructure.log.performance.interceptor.model;

import java.util.ArrayList;
import java.util.List;

import com.company.app.infrastructure.log.performance.PerformanceLogConfiguration;
import com.company.app.infrastructure.log.performance.interceptor.util.QueryInfoUtil;

/**
 * Select requests that are not in cache are considered
 * {@link PerformanceLogConfiguration}
 */
public class PreparedSQLCallsStatistic {

    private int total;
    private int select;
    private int update;
    private int insert;

    private final List<String> sqlList = new ArrayList<>();

    public void changeOnPrepareStatement(String sql) {
        if (sql.startsWith("select")) {
            this.select = select + 1;
        }
        if (sql.startsWith("update")) {
            this.update = update + 1;
        }
        if (sql.startsWith("insert")) {
            this.insert = insert + 1;
        }
        this.total = total + 1;
        this.sqlList.add(sql);
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();

        stringBuilder.append("total: [%s] (of them: select: [%s], update: [%s], insert: [%s]), sql:"
                .formatted(total, select, update, insert));

        var list = QueryInfoUtil.getSortedAndGroupedList(sqlList);
        list.forEach(pair -> stringBuilder.append("\n").append("x").append(pair.right).append(" ").append(pair.left));

        return stringBuilder.toString();
    }

}
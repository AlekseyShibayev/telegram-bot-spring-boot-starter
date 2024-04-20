package com.company.app.infrastructure.log.performance.interceptor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import com.company.app.infrastructure.log.performance.PerformanceLogConfiguration;
import com.company.app.infrastructure.log.performance.interceptor.model.QueryInfo;


/**
 * Before original method:
 * create key, with full class name + .method + # number, and put it into thread local map with new QueryInfo.
 * <p>
 * When method running:
 * intercept hibernate actions and modify all QueryInfo by current thread.
 * <p>
 * After original method:
 * gives filled QueryInfo, and remove value by key.
 */
@Slf4j
@Service
@ConditionalOnBean(PerformanceLogConfiguration.class)
public class HibernateQueryInfoInterceptor extends EmptyInterceptor {

    private static final ThreadLocal<Map<String, QueryInfo>> METHOD_NAME_VS_QUERY_INFO = ThreadLocal.withInitial(HashMap::new);
    private static final ThreadLocal<Long> METHOD_INVOCATION_ATTEMPT_COUNTER = ThreadLocal.withInitial(() -> 1L);

    public String createKey(MethodInvocation invocation) {
        Long number = METHOD_INVOCATION_ATTEMPT_COUNTER.get();
        String classAndMethodName = getClassAndMethodName(invocation);
        String key = classAndMethodName + "#" + number;

        Map<String, QueryInfo> map = METHOD_NAME_VS_QUERY_INFO.get();
        if (map.containsKey(key)) {
            throw new IllegalArgumentException("key [%s] already in map".formatted(key));
        }
        map.put(key, new QueryInfo());
        METHOD_INVOCATION_ATTEMPT_COUNTER.set(number + 1);

        return key;
    }

    public QueryInfo getQueryInfo(String key) {
        return METHOD_NAME_VS_QUERY_INFO.get().get(key);
    }

    public void remove(String key) {
        METHOD_NAME_VS_QUERY_INFO.get().remove(key);
    }

    private String getClassAndMethodName(MethodInvocation invocation) {
        String methodName = invocation.getMethod().getName();
        String className = invocation.getMethod().getDeclaringClass().getName();
        return className + "." + methodName;
    }

    /**
     * Intercept hibernate action and modify all QueryInfo by current thread.
     * <p>
     * We can look at any of the proxy methods in isolation, because each entry contains the statistics of the calls of the lower queries.
     * <p>
     * Example:
     * if we have 4 proxy method
     * getEntity() -> getUser() -> getRelations() -> getRelations()
     * result of statistic map is:
     * getEntity(): entity, user, relations, relations
     * getUser(): user, relations, relations
     * getRelations(): relations, relations
     * getRelations(): relations
     */
    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        Map<String, QueryInfo> map = METHOD_NAME_VS_QUERY_INFO.get();
        if (!map.isEmpty()) {
            for (QueryInfo queryInfo : map.values()) {
                queryInfo.changeOnLoad(entity);
            }
        }
        return super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public String onPrepareStatement(String sql) {
        Map<String, QueryInfo> map = METHOD_NAME_VS_QUERY_INFO.get();
        if (!map.isEmpty()) {
            for (QueryInfo queryInfo : map.values()) {
                queryInfo.changeOnPrepareStatement(sql);
            }
        }
        return super.onPrepareStatement(sql);
    }

}
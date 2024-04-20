package com.company.app.infrastructure.log.performance.interceptor.util;

import java.util.List;
import java.util.Map;

import com.company.app.infrastructure.log.performance.interceptor.util.QueryInfoUtil;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QueryInfoUtilTest {

    @Test
    void test() {
        List<String> strings = List.of("1", "2", "1");

        Map<String, Integer> stringIntegerMap = QueryInfoUtil.mapListToMapIdentityVsCounter(strings);

        Assertions.assertEquals(2, stringIntegerMap.get("1"));
        Assertions.assertEquals(1, stringIntegerMap.get("2"));
    }

    @Test
    void test2() {
        List<String> strings = List.of("1", "2", "1", "3", "3", "3");

        List<ImmutablePair<String, Integer>> immutablePairList = QueryInfoUtil.getSortedAndGroupedList(strings);

        Assertions.assertEquals("3", immutablePairList.get(0).getLeft());
    }

}
package com.company.app.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;


public interface Collections {

    /**
     * null       -> true
     * size == 0  -> true
     * size > 0   -> false
     */
    static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * null       -> false
     * size == 0  -> false
     * size > 0   -> true
     */
    static boolean isNotEmpty(Collection<?> collection) {
        return !CollectionUtils.isEmpty(collection);
    }

    @SafeVarargs
    static <T> List<T> list(T... t) {
        return Arrays.stream(t).collect(Collectors.toCollection(ArrayList::new));
    }

}
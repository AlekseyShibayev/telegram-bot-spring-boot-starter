package com.company.app.core.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

@UtilityClass
public class Collections {

    /**
     * null       -> true
     * size == 0  -> true
     * size > 0   -> false
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * null       -> false
     * size == 0  -> false
     * size > 0   -> true
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !CollectionUtils.isEmpty(collection);
    }

}

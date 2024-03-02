package com.company.app.core.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Strings {

    public static final String DEFAULT_DELIMITER = ";";

    public static String cutEnd(String string, int amount) {
        try {
            return string.substring(0, string.length() - amount);
        } catch (Exception exception) {
            throw new IllegalArgumentException(exception);
        }
    }

}
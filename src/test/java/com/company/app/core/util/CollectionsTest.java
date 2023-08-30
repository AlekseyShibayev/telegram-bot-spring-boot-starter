package com.company.app.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CollectionsTest {

    @Test
    void isNotEmpty_emptyList_test() {
        Assertions.assertFalse(Collections.isNotEmpty(new ArrayList<>()));
    }

    @Test
    void isNotEmpty_null_test() {
        Assertions.assertFalse(Collections.isNotEmpty(null));
    }

    @Test
    void isNotEmpty_notEmptyList_test() {
        List<String> list = new ArrayList<>();
        list.add("1");
        Assertions.assertTrue(Collections.isNotEmpty(list));
    }

}
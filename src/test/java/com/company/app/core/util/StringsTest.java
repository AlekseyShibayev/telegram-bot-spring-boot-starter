package com.company.app.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringsTest {

	@Test
	void cut_end_positive_test() {
		Assertions.assertEquals("123", Strings.cutEnd("12345", 2));
	}

	@Test
	void cut_end_negative_test() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> Strings.cutEnd("1", 2));
	}

	@Test
	void cut_end_null_test() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> Strings.cutEnd(null, 2));
	}
}
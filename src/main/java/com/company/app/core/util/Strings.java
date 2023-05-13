package com.company.app.core.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Strings {

	public static String cutEnd(String string, int amount) {
		return string.substring(0, string.length() - amount);
	}
}

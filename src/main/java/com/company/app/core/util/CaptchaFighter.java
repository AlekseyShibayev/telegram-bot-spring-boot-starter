package com.company.app.core.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@UtilityClass
public class CaptchaFighter {

	private static final Random RANDOM = new Random();

	@SneakyThrows
	public static void fight(int of, int to) {
		int sleepTime = of + getRandomInt(to - of);
		doLog(sleepTime);
		Thread.sleep(sleepTime);
	}

	private static void doLog(int sleepTime) {
		if (log.isDebugEnabled()) {
			Thread thread = Thread.currentThread();
			log.debug("[{}]: Сплю [{}] ms.", thread.getName(), sleepTime);
		}
	}

	private static int getRandomInt(int required) {
		int n = RANDOM.nextInt(required);
		return n + 1;
	}
}

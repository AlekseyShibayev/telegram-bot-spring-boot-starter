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
		doLog(of, to, sleepTime);
		Thread.sleep(sleepTime);
	}

	private static void doLog(int of, int to, int sleepTime) {
		if (log.isDebugEnabled()) {
			Thread thread = Thread.currentThread();
			log.debug("[{}]: Сплю от [{}] ms до [{}] ms: [{}] ms.",
					thread.getName(), of, to, sleepTime);
		}
	}

	private static int getRandomInt(int required) {
		int n = RANDOM.nextInt(required);
		return n + 1;
	}
}

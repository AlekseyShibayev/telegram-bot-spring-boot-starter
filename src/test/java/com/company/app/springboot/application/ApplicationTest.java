package com.company.app.springboot.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

class ApplicationTest extends SpringBootTestApplicationContext {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void context_mustRise() {
		Assertions.assertNotNull(applicationContext);
	}
}

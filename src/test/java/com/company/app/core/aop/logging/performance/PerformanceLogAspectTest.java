package com.company.app.core.aop.logging.performance;

import com.company.app.core.aop.logging.performance.testEntity.ExperimentContextChild;
import com.company.app.springboot.application.SpringBootApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;

import java.util.UUID;

/**
 * Все методы,
 * кроме "1. anyMethodNameWithEmptyAnnotation"
 * должны вытащить GUID.
 */
class PerformanceLogAspectTest extends SpringBootApplicationTest {

	@Autowired
	PerformanceLogAspectExample example;

	private static final String GUID = "11111111-1111-1111-1111-111111111111";

	@Test
	void withEmptyAnnotationTest(CapturedOutput capture) {
		ExperimentContextChild child = new ExperimentContextChild();
		child.setGuid(UUID.fromString(GUID));
		example.anyMethodNameWithEmptyAnnotation(child);
		Assertions.assertNotNull(capture.getOut());
	}

	@Test
	void testWithGuidAsParameter(CapturedOutput capture) {
		ExperimentContextChild child = new ExperimentContextChild();
		child.setGuid(UUID.fromString(GUID));
		example.anyMethodNameWithGuidAsParameter(child, GUID);
		Assertions.assertTrue(capture.getOut().contains(GUID));
	}

	@Test
	void testWithNumberAndMethodName(CapturedOutput capture) {
		ExperimentContextChild child = new ExperimentContextChild();
		child.setGuid(UUID.fromString(GUID));
		example.anyMethodNameWithNumberAndMethodName(child);
		Assertions.assertTrue(capture.getOut().contains(GUID));
	}

	@Test
	void testWithNumberAndFieldName(CapturedOutput capture) {
		ExperimentContextChild child = new ExperimentContextChild();
		child.setGuid(UUID.fromString(GUID));
		example.anyMethodNameWithNumberAndFieldName(child);
		Assertions.assertTrue(capture.getOut().contains(GUID));
	}
}
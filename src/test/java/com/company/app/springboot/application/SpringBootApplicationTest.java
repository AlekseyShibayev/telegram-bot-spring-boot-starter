package com.company.app.springboot.application;

import com.company.app.core.StarterConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.TestPropertySource;

/**
 * yml можно указать профилем
 * @ActiveProfiles("test") + application-test.yml
 * или
 * вот так, но я не проверял еще:
 * @TestPropertySource(properties = {"spring.config.location = src/test/resources/test.yml"})
 */
@SpringBootTest(
		classes = StarterConfiguration.class
)
@ExtendWith(OutputCaptureExtension.class)
@TestPropertySource("/test.properties")
public abstract class SpringBootApplicationTest {
}

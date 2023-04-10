package com.company.app.springboot.application;

import com.company.app.StarterConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(
		classes = StarterConfiguration.class
)
@ExtendWith(OutputCaptureExtension.class)
@TestPropertySource("/test.properties")
public abstract class SpringBootApplicationTest {
}

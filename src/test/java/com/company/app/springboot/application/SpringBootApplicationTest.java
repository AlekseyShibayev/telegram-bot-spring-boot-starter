package com.company.app.springboot.application;

import com.company.app.core.StarterConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
		classes = StarterConfiguration.class
)
@ExtendWith(OutputCaptureExtension.class)
@ActiveProfiles("test")
public abstract class SpringBootApplicationTest {
}

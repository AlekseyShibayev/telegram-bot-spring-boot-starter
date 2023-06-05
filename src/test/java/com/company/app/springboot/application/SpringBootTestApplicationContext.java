package com.company.app.springboot.application;

import com.company.app.core.StarterConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.PostConstruct;

/**
 * yml можно указать профилем
 *
 * @ActiveProfiles("test") + application-test.yml
 * или
 * вот так, но я не проверял еще:
 * @TestPropertySource(properties = {"spring.config.location = src/test/resources/test.yml"})
 */

@Slf4j
@SpringBootTest(
        classes = StarterConfiguration.class
)
@ExtendWith(OutputCaptureExtension.class)
@TestPropertySource("/test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class SpringBootTestApplicationContext {

    @PostConstruct
    void init() {
        log.debug("**********     запущена группа тестов всего приложения     **********");
    }
}

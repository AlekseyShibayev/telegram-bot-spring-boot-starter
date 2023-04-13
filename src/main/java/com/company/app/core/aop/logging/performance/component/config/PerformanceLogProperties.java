package com.company.app.core.aop.logging.performance.component.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * автокомплит application.properties только в Ultimate IDEA
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "performance.log.annotation")
public class PerformanceLogProperties {

	boolean enable;
}

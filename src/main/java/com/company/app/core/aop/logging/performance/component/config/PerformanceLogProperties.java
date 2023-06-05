package com.company.app.core.aop.logging.performance.component.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * автокомплит application.properties только в Ultimate IDEA
 */
@Data
@ConfigurationProperties(prefix = "performance.log.annotation")
public class PerformanceLogProperties {

    boolean enable;
}

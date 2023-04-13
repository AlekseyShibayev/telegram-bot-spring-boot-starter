package com.company.app.core;

import com.company.app.core.aop.logging.performance.component.config.PerformanceLogProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
@EnableConfigurationProperties(PerformanceLogProperties.class)
public class StarterConfiguration {
}

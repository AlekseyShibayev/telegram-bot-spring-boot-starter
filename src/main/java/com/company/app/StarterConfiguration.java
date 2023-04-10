package com.company.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
//@EnableConfigurationProperties(ConfigurationProperties.class)
@ComponentScan
public class StarterConfiguration {
}

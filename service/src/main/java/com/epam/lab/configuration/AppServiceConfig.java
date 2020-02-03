package com.epam.lab.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

@Configuration
@ComponentScan("com.epam.lab.service")
@ContextConfiguration(classes = {AppConfig.class})
public class AppServiceConfig {

}

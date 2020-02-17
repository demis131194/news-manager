package com.epam.lab.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.epam.lab.controller", "com.epam.lab.exception"})
@Import(AppServiceConfig.class)
public class AppContextConfig {
}

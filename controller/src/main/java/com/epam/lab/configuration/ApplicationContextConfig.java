package com.epam.lab.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.epam.lab.controller")
@Import(AppServiceConfig.class)
@EnableWebMvc
public class ApplicationContextConfig {
}

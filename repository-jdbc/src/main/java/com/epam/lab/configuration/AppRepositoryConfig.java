package com.epam.lab.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan("com.epam.lab.repository")
@PropertySource("classpath:db/datasource.properties")
public class AppRepositoryConfig {
    private final String DATA_SOURCE_PROPERTY = "/db/datasource.properties";

    @Bean(name = "data-source", destroyMethod = "close")
    public HikariDataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig(DATA_SOURCE_PROPERTY);
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "jdbc-template")
    public JdbcTemplate jdbcTemplate(HikariDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

package com.epam.lab.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.epam.lab.repository")
@PropertySource("classpath:db/datasource.properties")
public class AppRepositoryConfig {
    private final String DATA_SOURCE_PROPERTY = "/db/datasource.properties";

    @Autowired
    private Environment env;

    @Bean(name = "hikariCP-config")
    public HikariConfig hikariConfig() {
        return new HikariConfig(DATA_SOURCE_PROPERTY);
    }

    @Bean(name = "data-source")
    public DataSource dataSource(@Autowired HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "jdbc-template")
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}

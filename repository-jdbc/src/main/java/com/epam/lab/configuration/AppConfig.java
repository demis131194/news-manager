package com.epam.lab.configuration;

import com.epam.lab.repository.mapper.AuthorRowMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.epam.lab.repository")
public class AppConfig {
    private final String DATA_SOURCE_PROPERTY = "/db/datasource.properties";

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

    @Bean(name = "author-row-mapper")
    public AuthorRowMapper authorRowMapper() {
        return new AuthorRowMapper();
    }

}

package com.epam.lab.configuration;

import com.epam.lab.repository.AuthorRepository;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class TestDataSourceConfig {
    private static final String INIT_DB_SCRIPT_PATH = "db/init-db.sql";

    @Bean
    public DataSource dataSource() {                        // FIXME: 1/31/2020
        DataSource dataSource = null;
        try {
            dataSource = EmbeddedPostgres.start().getPostgresDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public AuthorRepository authorRepository() {
        return new AuthorRepository();
    }
}

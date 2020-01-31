package com.epam.lab.repository;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class DataSourceInMemory {
    private static final String INIT_DB_SCRIPT_PATH = "db/init-db.sql";
    private static DataSourceInMemory dataSourceInMemory = new DataSourceInMemory();
    private JdbcTemplate jdbcTemplate;

    private DataSourceInMemory() {
        initDataBase();
    }

    public static JdbcTemplate getJdbcTemplate() {
        return dataSourceInMemory.jdbcTemplate;
    }

    private void initDataBase() {
        try {
            InputStream resourceAsStream = DataSourceInMemory.class.getClassLoader().getResourceAsStream(INIT_DB_SCRIPT_PATH);
            String script = new BufferedReader(new InputStreamReader(Objects.requireNonNull(resourceAsStream))).lines().reduce("", String::concat);
            jdbcTemplate = new JdbcTemplate(EmbeddedPostgres.start().getPostgresDatabase());
            jdbcTemplate.execute(script);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

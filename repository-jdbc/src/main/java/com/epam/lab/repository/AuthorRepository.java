package com.epam.lab.repository;

import com.epam.lab.model.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("author-repository")
public class AuthorRepository implements BaseCrudRepository<Author> {
    private static final Logger logger = LogManager.getLogger(AuthorRepository.class);

    private static final String INSERT_QUERY = "INSERT INTO authors (name, surname) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE authors SET name = ?, surname = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM authors WHERE id = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT id, name, surname FROM authors WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT id, name, surname FROM authors";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Author> rowMapper;

    @Override
    public void create(Author obj) {
        int result = jdbcTemplate.update(INSERT_QUERY, obj.getName(), obj.getSurname());
        logger.info("Creating author result : {}", result);                   // FIXME: 1/30/2020
    }

    @Override
    public void update(Author obj) {
        int result = jdbcTemplate.update(UPDATE_QUERY, obj.getName(), obj.getSurname(), obj.getId());
        logger.info("Updating author result : {}", result);                   // FIXME: 1/30/2020
    }

    @Override
    public void delete(long id) {
        int result = jdbcTemplate.update(DELETE_QUERY, id);
        logger.info("Delete author result : {}", result);                   // FIXME: 1/30/2020
    }

    @Override
    public Author findById(long id) {
        Author result = jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new Object[]{id}, rowMapper);
        logger.info("Find author result : {}", result);                   // FIXME: 1/30/2020
        return result;
    }

    @Override
    public List<Author> findAll() {
        List<Author> result = jdbcTemplate.query(FIND_ALL_QUERY, rowMapper);
        logger.info("Find all result : {}", result);                   // FIXME: 1/30/2020
        return result;
    }
}

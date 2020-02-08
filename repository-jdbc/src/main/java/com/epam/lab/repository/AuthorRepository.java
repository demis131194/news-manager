package com.epam.lab.repository;

import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.mapper.AuthorRowMapper;
import com.epam.lab.repository.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository("author-repository")
public class AuthorRepository implements SpecificationRepository<Author> {
    private static final Logger logger = LogManager.getLogger(AuthorRepository.class);

    private static final String INSERT_QUERY = "INSERT INTO authors (name, surname) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE authors SET name = ?, surname = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM authors WHERE id = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT id, name, surname FROM authors WHERE id = ?";
    private static final String FIND_AUTHOR_BY_NAME_QUERY = "SELECT id, name, surname FROM authors WHERE name = ?";
    private static final String FIND_AUTHOR_BY_SURNAME_QUERY = "SELECT id, name, surname FROM authors WHERE surname = ?";
    private static final String FIND_ALL_QUERY = "SELECT id, name, surname FROM authors";
    private static final String COUNT_ALL_QUERY = "SELECT COUNT(id) FROM authors";

    private JdbcTemplate jdbcTemplate;

    private RowMapper<Author> rowMapper;

    @Autowired
    public AuthorRepository(JdbcTemplate jdbcTemplate, RowMapper<Author> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public long create(Author obj) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_QUERY, new String[] {AuthorRowMapper.ID_COLUMN});
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getSurname());
            return ps;
        }, keyHolder);

        long newId = keyHolder.getKey().longValue();
        logger.info("Creating author id : {}", newId);                   // FIXME: 1/30/2020
        return newId;
    }

    @Override
    public boolean update(Author obj) {
        int result = jdbcTemplate.update(UPDATE_QUERY, obj.getName(), obj.getSurname(), obj.getId());
        logger.info("Updating author result : {}", result);                   // FIXME: 1/30/2020
        return result == 1;
    }

    @Override
    public boolean delete(long id) {
        int result = jdbcTemplate.update(DELETE_QUERY, id);
        logger.info("Delete author result : {}", result);                   // FIXME: 1/30/2020
        return result == 1;
    }

    @Override
    public Author findById(long id) {
        Author result;
        try {
            result = jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new Object[]{id}, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            result = null;
        }
        logger.info("Find author result : {}", result);                   // FIXME: 1/30/2020
        return result;
    }

    @Override
    public List<Author> findAll() {
        List<Author> result = jdbcTemplate.query(FIND_ALL_QUERY, rowMapper);
        logger.info("Find all authors result : {}", result);                   // FIXME: 1/30/2020
        return result;
    }

    @Override
    public List<Author> findBySpecification(Specification specification) {
        List<Author> result = jdbcTemplate.query(specification.query(), rowMapper);
        logger.info("Find authors by {}, result : {}", specification.getClass().getSimpleName(), result);
        return result;
    }

    @Override
    public int countAll() {
        int result = jdbcTemplate.queryForObject(COUNT_ALL_QUERY, Integer.class);
        logger.info("Count all authors result : {}", result);                   // FIXME: 1/30/2020
        return result;
    }
}

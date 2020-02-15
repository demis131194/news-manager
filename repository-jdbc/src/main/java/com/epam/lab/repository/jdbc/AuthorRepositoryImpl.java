package com.epam.lab.repository.jdbc;

import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.jdbc.mapper.AuthorRowMapper;
import com.epam.lab.repository.jdbc.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * The type Author repository.
 */
@Repository("author-repository")
public class AuthorRepositoryImpl implements AuthorRepository {
    private static final Logger logger = LogManager.getLogger(AuthorRepositoryImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO authors (name, surname) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE authors SET name = ?, surname = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM authors WHERE id = ?";
    private static final String COUNT_ALL_QUERY = "SELECT COUNT(id) FROM authors";

    private JdbcTemplate jdbcTemplate;

    private RowMapper<Author> rowMapper;

    /**
     * Instantiates a new Author repository.
     *
     * @param jdbcTemplate the jdbc template
     * @param rowMapper    the row mapper
     */
    @Autowired
    public AuthorRepositoryImpl(JdbcTemplate jdbcTemplate, RowMapper<Author> rowMapper) {
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
        logger.info("Creating author id : {}", newId);                   
        return newId;
    }

    @Override
    public boolean update(Author obj) {
        int result = jdbcTemplate.update(UPDATE_QUERY, obj.getName(), obj.getSurname(), obj.getId());
        logger.info("Updating author result : {}", result);                   
        return result == 1;
    }

    @Override
    public boolean delete(long id) {
        int result = jdbcTemplate.update(DELETE_QUERY, id);
        logger.info("Delete author result : {}", result);                   
        return result == 1;
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
        logger.info("Count all authors result : {}", result);                   
        return result;
    }
}

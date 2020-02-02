package com.epam.lab.repository;

import com.epam.lab.model.Author;
import com.epam.lab.repository.mapper.AuthorRowMapper;
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

@Repository("author-repository")
public class AuthorRepository implements BaseCrudRepository<Author> {
    private static final Logger logger = LogManager.getLogger(AuthorRepository.class);

    private static final String INSERT_QUERY = "INSERT INTO authors (name, surname) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE authors SET name = ?, surname = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM authors WHERE id = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT id, name, surname FROM authors WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT id, name, surname FROM authors";
    private static final String FIND_BY_NEWS_ID_QUERY = "SELECT nw_a.author_id AS id, a.name, a.surname FROM news_authors nw_a LEFT JOIN authors a ON nw_a.news_id = a.id WHERE news_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Author> rowMapper = new AuthorRowMapper();

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
        Author result = jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new Object[]{id}, rowMapper);
        logger.info("Find author result : {}", result);                   // FIXME: 1/30/2020
        return result;
    }

    @Override
    public List<Author> findAll() {
        List<Author> result = jdbcTemplate.query(FIND_ALL_QUERY, rowMapper);
        logger.info("Find all authors result : {}", result);                   // FIXME: 1/30/2020
        return result;
    }

    public List<Author> findByNewsId(long id) {
        List<Author> result = jdbcTemplate.query(FIND_BY_NEWS_ID_QUERY, new Object[]{id}, rowMapper);
        logger.info("Find author by news id result : {}", result);                   // FIXME: 1/30/2020
        return result;
    }
}

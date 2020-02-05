package com.epam.lab.repository;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.mapper.TagRowMapper;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("tag-repository")
public class TagRepository implements BaseCrudRepository<Tag> {
    private static final Logger logger = LogManager.getLogger(TagRepository.class);

    private static final String INSERT_QUERY = "INSERT INTO tags (name) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE tags SET name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM tags WHERE id = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT id, name FROM tags WHERE id = ?";
    private static final String FIND_TAG_BY_NAME_QUERY = "SELECT id, name FROM tags WHERE name = ?";
    private static final String FIND_ALL_QUERY = "SELECT id, name FROM tags";
    private static final String COUNT_ALL_QUERY = "SELECT COUNT(id) FROM tags";
    private static final String FIND_ALL_BY_NEWS_ID_QUERY = "SELECT nw_t.tag_id AS id, t.name FROM news_tags nw_t LEFT JOIN tags t on nw_t.tag_id = t.id WHERE nw_t.news_id = ?";

    private JdbcTemplate jdbcTemplate;

    private RowMapper<Tag> rowMapper;

    @Autowired
    public TagRepository(JdbcTemplate jdbcTemplate, RowMapper<Tag> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public long create(Tag obj) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_QUERY, new String[] {TagRowMapper.ID_COLUMN});
            ps.setString(1, obj.getName());
            return ps;
        }, keyHolder);

        long newId = keyHolder.getKey().longValue();
        logger.info("Creating tag id : {}", newId);                   // FIXME: 1/31/2020
        return newId;
    }

    @Override
    public boolean update(Tag obj) {
        int result = jdbcTemplate.update(UPDATE_QUERY, obj.getName(), obj.getId());
        logger.info("Updating tag result : {}", result);                   // FIXME: 1/31/2020
        return result == 1;
    }

    @Override
    public boolean delete(long id) {
        int result = jdbcTemplate.update(DELETE_QUERY, id);
        logger.info("Delete tags result : {}", result);                   // FIXME: 1/31/2020
        return result == 1;
    }

    @Override
    public Tag findById(long id) {
        Tag result;
        try {
            result = jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new Object[]{id}, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            result = null;
        }
        logger.info("Find tag result : {}", result);                   // FIXME: 1/31/2020
        return result;
    }

    @Override
    public List<Tag> findAll() {
        List<Tag> result = jdbcTemplate.query(FIND_ALL_QUERY, rowMapper);
        logger.info("Find all tags result : {}", result);                   // FIXME: 1/31/2020
        return result;
    }

    @Override
    public int countAll() {
        int result = jdbcTemplate.queryForObject(COUNT_ALL_QUERY, Integer.class);
        logger.info("Count all tags result : {}", result);                   // FIXME: 1/31/2020
        return result;
    }

    public Set<Tag> findTagsByNewsId(long newsId) {
        List<Tag> queryResult = jdbcTemplate.query(FIND_ALL_BY_NEWS_ID_QUERY, new Object[]{newsId}, rowMapper);
        Set<Tag> result = new HashSet<>(queryResult);
        logger.info("Find all tags by News Id result : {}", result);                   // FIXME: 1/31/2020
        return result;
    }

    public Tag findTagByName(String tagName) {                 // FIXME: 2/5/2020 Catch exception?
        Tag result;
        try {
            result = jdbcTemplate.queryForObject(FIND_TAG_BY_NAME_QUERY, new Object[]{tagName}, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            result = null;
        }
        logger.info("Find tag by name result : {}", result);                   // FIXME: 1/31/2020
        return result;
    }
}

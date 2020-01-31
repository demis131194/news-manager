package com.epam.lab.repository;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.mapper.TagRowMapper;
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

@Repository("tag-repository")
public class TagRepository implements BaseCrudRepository<Tag> {
    private static final Logger logger = LogManager.getLogger(TagRepository.class);

    private static final String INSERT_QUERY = "INSERT INTO tags (name) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE tags SET name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM tags WHERE id = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT id, name FROM tags WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT id, name FROM tags";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Tag> rowMapper = new TagRowMapper();

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
        Tag result = jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new Object[]{id}, rowMapper);
        logger.info("Find tag result : {}", result);                   // FIXME: 1/31/2020
        return result;
    }

    @Override
    public List<Tag> findAll() {
        List<Tag> result = jdbcTemplate.query(FIND_ALL_QUERY, rowMapper);
        logger.info("Find all result : {}", result);                   // FIXME: 1/31/2020
        return result;
    }
}

package com.epam.lab.repository.jdbc;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.repository.jdbc.mapper.TagRowMapper;
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
 * The type Tag repository.
 */
@Repository("tag-repository")
public class TagRepositoryImpl implements TagRepository {
    private static final Logger logger = LogManager.getLogger(TagRepositoryImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO tags (name) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE tags SET name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM tags WHERE id = ?";
    private static final String COUNT_ALL_QUERY = "SELECT COUNT(id) FROM tags";

    private JdbcTemplate jdbcTemplate;

    private RowMapper<Tag> rowMapper;

    /**
     * Instantiates a new Tag repository.
     *
     * @param jdbcTemplate the jdbc template
     * @param rowMapper    the row mapper
     */
    @Autowired
    public TagRepositoryImpl(JdbcTemplate jdbcTemplate, RowMapper<Tag> rowMapper) {
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
        logger.debug("Creating tag id : {}", newId);
        return newId;
    }

    @Override
    public boolean update(Tag obj) {
        int result = jdbcTemplate.update(UPDATE_QUERY, obj.getName(), obj.getId());
        logger.debug("Updating tag result : {}", result);
        return result == 1;
    }

    @Override
    public boolean delete(long id) {
        int result = jdbcTemplate.update(DELETE_QUERY, id);
        logger.debug("Delete tags result : {}", result);
        return result == 1;
    }

    @Override
    public List<Tag> findBySpecification(Specification specification) {
        List<Tag> result = jdbcTemplate.query(specification.query(), rowMapper);
        logger.debug("Find tags by {}, result : {}", specification.getClass().getSimpleName(), result);
        return result;
    }

    @Override
    public long countAll() {
        long result = jdbcTemplate.queryForObject(COUNT_ALL_QUERY, Long.class);
        logger.debug("Count all tags result : {}", result);
        return result;
    }
}

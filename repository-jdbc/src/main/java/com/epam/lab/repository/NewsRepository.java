package com.epam.lab.repository;

import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.repository.mapper.NewsRowMapper;
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

@Repository("news-repository")
public class NewsRepository implements BaseCrudRepository<News> {
    private static final Logger logger = LogManager.getLogger(NewsRepository.class);

    private static final String INSERT_QUERY = "INSERT INTO news (title, short_text, full_text) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE news SET title = ?, short_text = ?, full_text = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM news WHERE id = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT id, title, short_text, full_text, creation_date, modification_date FROM news WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT id, title, short_text, full_text, creation_date, modification_date FROM news";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<News> rowMapper = new NewsRowMapper();

    @Override
    public long create(News obj) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_QUERY, new String[] {NewsRowMapper.ID_COLUMN});
            ps.setString(1, obj.getTitle());
            ps.setString(2, obj.getShortText());
            ps.setString(3, obj.getFullText());
            return ps;
        }, keyHolder);

        long generatedId = keyHolder.getKey().longValue();
        logger.info("Creating news id : {}", generatedId);                   // FIXME: 1/31/2020
        return generatedId;
    }

    @Override
    public boolean update(News obj) {
        int result = jdbcTemplate.update(UPDATE_QUERY, obj.getTitle(), obj.getShortText(), obj.getFullText(), obj.getId());
        logger.info("Updating news result : {}", result);                   // FIXME: 1/30/2020
        return result == 1;
    }

    @Override
    public boolean delete(long id) {
        int result = jdbcTemplate.update(DELETE_QUERY, id);
        logger.info("Delete news result : {}", result);                   // FIXME: 1/30/2020
        return result == 1;
    }

    @Override
    public News findById(long id) {
        News result = jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new Object[]{id}, rowMapper);
        logger.info("Find news result : {}", result);                   // FIXME: 1/30/2020
        return result;
    }

    @Override
    public List<News> findAll() {
        List<News> result = jdbcTemplate.query(FIND_ALL_QUERY, rowMapper);
        logger.info("Find all news result : {}", result);                   // FIXME: 1/30/2020
        return result;
    }
}

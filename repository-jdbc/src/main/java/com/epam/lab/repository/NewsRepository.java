package com.epam.lab.repository;

import com.epam.lab.model.News;
import com.epam.lab.repository.mapper.NewsRowMapper;
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
import java.util.List;

@Repository("news-repository")
public class NewsRepository implements SpecificationRepository<News> {
    private static final Logger logger = LogManager.getLogger(NewsRepository.class);

    private static final String INSERT_QUERY = "INSERT INTO news (title, short_text, full_text) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE news SET title = ?, short_text = ?, full_text = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM news WHERE id = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT id, title, short_text, full_text, creation_date, modification_date FROM news WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT id, title, short_text, full_text, creation_date, modification_date FROM news";
    private static final String FIND_ALL_BY_TAG_QUERY = "SELECT id, title, short_text, full_text, creation_date, modification_date FROM news n LEFT JOIN news_tags nt on n.id = nt.news_id WHERE tag_id = ?";
    private static final String CREATE_NEWS_TAG_BOUND_QUERY = "INSERT INTO news_tags (news_id, tag_id) VALUES (?, ?)";
    private static final String DELETE_NEWS_TAG_BOUND_QUERY = "DELETE FROM news_tags WHERE news_id = ? AND tag_id = ?";
    private static final String DELETE_ALL_NEWS_TAG_BOUNDS_QUERY = "DELETE FROM news_tags WHERE news_id = ?";
    private static final String CREATE_NEWS_AUTHOR_BOUND_QUERY = "INSERT INTO news_authors (news_id, author_id) VALUES (?, ?)";
    private static final String UPDATE_NEWS_AUTHOR_BOUND_QUERY = "UPDATE news_authors SET author_id = ? WHERE news_id = ?";
    private static final String DELETE_NEWS_AUTHOR_BOUND_QUERY = "DELETE FROM news_authors WHERE news_id = ?";
    private static final String COUNT_ALL_NEWS_QUERY = "SELECT COUNT(id) FROM news";
    private static final String FIND_AUTHOR_BY_NEWS_ID_QUERY = "SELECT author_id FROM news_authors WHERE news_id = ?";
    private static final String FIND_TAGS_BY_NEWS_ID_QUERY = "SELECT tag_id FROM news_tags WHERE news_id = ?";

    private JdbcTemplate jdbcTemplate;

    private RowMapper<News> rowMapper;

    @Autowired
    public NewsRepository(JdbcTemplate jdbcTemplate, RowMapper<News> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

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
        News result;
        try {
            result = jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new Object[]{id}, rowMapper);
        } catch (DataAccessException e) {
            result = null;
        }
        logger.info("Find news result : {}", result);                   // FIXME: 1/30/2020
        return result;
    }

    @Override
    public List<News> findAll() {
        List<News> result = jdbcTemplate.query(FIND_ALL_QUERY, rowMapper);
        logger.info("Find all news result : {}", result);                   // FIXME: 1/30/2020
        return result;
    }

    @Override
    public List<News> findBySpecification(Specification specification) {
        return null;
    }

    @Override
    public int countAll() {
        int result = jdbcTemplate.queryForObject(COUNT_ALL_NEWS_QUERY, Integer.class);
        logger.info("Count all news result : {}", result);
        return result;
    }

    public List<News> findAll(SearchCriteria searchCriteria) {
        return null;
    }

    public List<News> findAllByTagId(long tagId) {
        List<News> result = jdbcTemplate.query(FIND_ALL_BY_TAG_QUERY, new Object[]{tagId}, rowMapper);
        logger.info("Find all news By Tag id {}, result : {}", tagId, result);                   // FIXME: 1/30/2020
        return result;
    }

    public boolean createNewsTagBound(long newsId, long tagId) {
        int update = jdbcTemplate.update(CREATE_NEWS_TAG_BOUND_QUERY, newsId, tagId);
        logger.info("Create news tag bound result : {}", update);                   // FIXME: 1/30/2020
        return update == 1;
    }

    public boolean createNewsAuthorBound(long newsId, long authorId) {
        int update = jdbcTemplate.update(CREATE_NEWS_AUTHOR_BOUND_QUERY, newsId, authorId);
        logger.info("Create news author bound result : {}", update);                   // FIXME: 1/30/2020
        return update == 1;
    }

    public boolean updateNewsAuthorBound(long newsId, long authorId) {
        int update = jdbcTemplate.update(UPDATE_NEWS_AUTHOR_BOUND_QUERY, authorId , newsId);
        logger.info("Update news tag bound result : {}", update);                   // FIXME: 1/30/2020
        return update == 1;
    }

    public boolean deleteNewsTagBound(long newsId, long tagId) {
        int delete = jdbcTemplate.update(DELETE_NEWS_TAG_BOUND_QUERY, newsId, tagId);
        logger.info("Delete news tag bound result : {}", delete);                   // FIXME: 1/30/2020
        return delete == 1;
    }

    public boolean deleteAllNewsTagBounds(long newsId) {
        int delete = jdbcTemplate.update(DELETE_ALL_NEWS_TAG_BOUNDS_QUERY, newsId);
        logger.info("Delete all news tag bounds result : {}", delete);                   // FIXME: 1/30/2020
        return delete != 0;
    }

    public boolean deleteNewsAuthorBound(long newsId) {
        int delete = jdbcTemplate.update(DELETE_NEWS_AUTHOR_BOUND_QUERY, newsId);
        logger.info("Delete news tag bound result : {}", delete);                   // FIXME: 1/30/2020
        return delete == 1;
    }

    public Long findAuthorIdByNewsId(long newsId) {
        Long authorId;
        try {
            authorId = jdbcTemplate.queryForObject(FIND_AUTHOR_BY_NEWS_ID_QUERY, new Object[]{newsId}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            authorId = null;
        }
        logger.info("Find author id by news id result : {}", authorId);
        return authorId;
    }

    public List<Long> findTagsIdByNewsId(long newsId) {
        List<Long> tagsId = jdbcTemplate.queryForList(FIND_TAGS_BY_NEWS_ID_QUERY, new Object[]{newsId}, Long.class);
        logger.info("Find tags id by news id result : {}", tagsId);
        return tagsId;
    }
}

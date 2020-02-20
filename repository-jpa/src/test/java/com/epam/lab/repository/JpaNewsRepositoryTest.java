package com.epam.lab.repository;

import com.epam.lab.configuration.AppJpaTestConfiguration;
import com.epam.lab.model.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

import static com.epam.lab.DbTestObjects.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppJpaTestConfiguration.class)
@Sql(scripts = "classpath:db/test-init-db.sql")
public class JpaNewsRepositoryTest {

    @Autowired
    private NewsRepository newsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void saveCreateTest() {
        News create = new News("Create title", "Create short text", "Create full text",
                EXPECTED_AUTHOR_2, Arrays.asList(EXPECTED_TAG_2, EXPECTED_TAG_6));
        News expected = newsRepository.save(create);
        News actual = entityManager.find(News.class, expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void saveUpdateTest() {
        News create = new News(EXPECTED_NEWS_1.getId(),"Update title", "Update short text", "Update full text",
                EXPECTED_AUTHOR_2, Arrays.asList(EXPECTED_TAG_1, EXPECTED_TAG_4, EXPECTED_TAG_5));
        News expected = newsRepository.save(create);
        News actual = entityManager.find(News.class, expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTest() {
        boolean isDelete = newsRepository.delete(EXPECTED_NEWS_1.getId());
        assertTrue(isDelete);
    }

    @Test
    public void findNewsByIdTest() {
        News actual = newsRepository.findById(EXPECTED_NEWS_1.getId());
        assertEquals(EXPECTED_NEWS_1, actual);
    }

    @Test
    public void findAllNewsTest() {
        List<News> expected = Arrays.asList(EXPECTED_NEWS_1, EXPECTED_NEWS_2, EXPECTED_NEWS_3, EXPECTED_NEWS_4, EXPECTED_NEWS_5,
                EXPECTED_NEWS_6, EXPECTED_NEWS_7, EXPECTED_NEWS_8, EXPECTED_NEWS_9, EXPECTED_NEWS_10, EXPECTED_NEWS_11);
        List<News> actual = newsRepository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countAllTest() {
        long actual = newsRepository.countAll();
        assertEquals(EXPECTED_COUNT_ALL_NEWS, actual);
    }


}
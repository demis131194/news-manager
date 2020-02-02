package com.epam.lab.repository;

import com.epam.lab.configuration.TestDataSourceConfig;
import com.epam.lab.model.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class})
@Sql(scripts = "classpath:db/test-init-db.sql")
public class NewsRepositoryTest {

    @Autowired
    private NewsRepository newsRepository;

    private final long INIT_KEY_ID = 1L;

    @Test
    public void createTest() {
        News testNews = new News("Test title", "Test short text", "Test full text");
        long generatedKey = newsRepository.create(testNews);
        assertNotEquals(0, generatedKey);
    }

    @Test
    public void updateTest() {
        News testNews = new News(INIT_KEY_ID, "Test update title", "Test update short text", "Test update full text");
        boolean isUpdate = newsRepository.update(testNews);
        assertTrue(isUpdate);
    }

    @Test
    public void deleteTest() {
        boolean isDeleted = newsRepository.delete(INIT_KEY_ID);
        assertTrue(isDeleted);
    }

    @Test
    public void findByIdTest() {
        long id = INIT_KEY_ID;
        News expected = new News(id, "News title 1", "Short text 1", "Full text 1");
        News actual = newsRepository.findById(id);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllTest() {
        long startKey = INIT_KEY_ID;
        List<News> expected = Arrays.asList(
                new News(startKey, "News title 1", "Short text 1", "Full text 1"),
                new News(startKey + 1, "News title 2", "Short text 2", "Full text 2"),
                new News(startKey + 2, "News title 3", "Short text 3", "Full text 3")
        );
        List<News> actual = newsRepository.findAll();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createFailNullFieldTest() {
        News testNews = new News(null, "Short text 1", null);
        long generatedKey = newsRepository.create(testNews);
    }

    @Test
    public void updateFailWrongIdTest() {
        News testTag = new News(0L, "Test update title", "Test update short text", "Test update full text");
        boolean isUpdate = newsRepository.update(testTag);
        assertFalse(isUpdate);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void updateFailNullFieldTest() {
        News testNews = new News(INIT_KEY_ID, null, null, "Test update full text");
        newsRepository.update(testNews);
    }

    @Test
    public void deleteFailWrongIdTest() {
        boolean isDeleted = newsRepository.delete(INIT_KEY_ID - 1);
        assertFalse(isDeleted);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findByIdFailWrongIdTest() {
        News actual = newsRepository.findById(INIT_KEY_ID - 1);
    }

    @Test
    public void findAllByTagIdTest() {
        List<News> allByTagId = newsRepository.findAllByTagId(1);
        assertEquals(2, allByTagId.size());
    }

}
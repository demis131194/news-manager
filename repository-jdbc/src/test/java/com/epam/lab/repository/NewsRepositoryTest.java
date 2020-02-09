package com.epam.lab.repository;

import com.epam.lab.configuration.TestRepositoryConfig;
import com.epam.lab.model.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.epam.lab.repository.DbTestObjects.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestRepositoryConfig.class})
@Sql(scripts = "classpath:db/test-init-db.sql")
public class NewsRepositoryTest {

    @Autowired
    private NewsRepository newsRepository;

    @Test
    public void createTest() {
        News testNews = new News("Test title", "Test short text", "Test full text");
        long generatedKey = newsRepository.create(testNews);
        assertEquals(INIT_TEST_ID + EXPECTED_COUNT_ALL_NEWS, generatedKey);
    }

    @Test
    public void updateTest() {
        News testNews = new News(INIT_TEST_ID, "Test update title", "Test update short text", "Test update full text");
        boolean isUpdate = newsRepository.update(testNews);
        assertTrue(isUpdate);
    }

    @Test
    public void deleteTest() {
        boolean isDeleted = newsRepository.delete(INIT_TEST_ID);
        assertTrue(isDeleted);
    }

    @Test
    public void findByIdTest() {
        News actual = newsRepository.findById(INIT_TEST_ID);
        assertEquals(EXPECTED_NEWS_1, actual);
    }

    @Test
    public void findAllTest() {
        List<News> expected = Arrays.asList(
                EXPECTED_NEWS_1, EXPECTED_NEWS_2, EXPECTED_NEWS_3, EXPECTED_NEWS_4,
                EXPECTED_NEWS_5, EXPECTED_NEWS_6, EXPECTED_NEWS_7, EXPECTED_NEWS_8,
                EXPECTED_NEWS_9, EXPECTED_NEWS_10, EXPECTED_NEWS_11
        );
        List<News> actual = newsRepository.findAll();
        assertEquals(expected, actual);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createFailNullFieldTest() {
        News testNews = new News(null, "Short text 1", null);
        long generatedKey = newsRepository.create(testNews);
    }

    @Test
    public void updateFailWrongIdTest() {
        News testTag = new News(INIT_TEST_ID - 1, "Test update title", "Test update short text", "Test update full text");
        boolean isUpdate = newsRepository.update(testTag);
        assertFalse(isUpdate);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void updateFailNullFieldTest() {
        News testNews = new News(INIT_TEST_ID, null, null, "Test update full text");
        newsRepository.update(testNews);
    }

    @Test
    public void deleteFailWrongIdTest() {
        boolean isDeleted = newsRepository.delete(INIT_TEST_ID - 1);
        assertFalse(isDeleted);
    }

    @Test
    public void findByIdFailWrongIdTest() {
        News actual = newsRepository.findById(INIT_TEST_ID - 1);
        assertNull(actual);
    }

    @Test
    public void createNewsTagBoundTest() {
        boolean isCreated = newsRepository.createNewsTagBound(EXPECTED_NEWS_2.getId(), EXPECTED_TAG_1.getId());
        assertTrue(isCreated);
    }

    @Test
    public void createNewsAuthorBoundTest() {
        boolean created = newsRepository.createNewsAuthorBound(EXPECTED_NEWS_3.getId(), EXPECTED_AUTHOR_3.getId());
        assertTrue(created);
    }

    @Test
    public void countAllTest() {
        long count = newsRepository.countAll();
        assertEquals(EXPECTED_COUNT_ALL_NEWS, count);
    }

    @Test
    public void deleteNewsTagBoundTest() {
        boolean isDelete = newsRepository.deleteNewsTagBound(EXPECTED_NEWS_1.getId(), EXPECTED_TAG_2.getId());
        assertTrue(isDelete);
    }

    @Test
    public void deleteNewsTagBoundFailTest() {
        boolean isDelete = newsRepository.deleteNewsTagBound(EXPECTED_NEWS_1.getId(), EXPECTED_TAG_4.getId());
        assertFalse(isDelete);
    }

    @Test
    public void deleteAllNewsTagBoundsTest() {
        boolean isDelete = newsRepository.deleteAllNewsTagBounds(EXPECTED_NEWS_1.getId());
        assertTrue(isDelete);
    }

    @Test
    public void deleteAllNewsTagBoundsFailTest() {
        boolean isDelete = newsRepository.deleteAllNewsTagBounds(INIT_TEST_ID - 1);
        assertFalse(isDelete);
    }

    @Test
    public void deleteNewsAuthorBoundTest() {
        boolean isDelete = newsRepository.deleteNewsAuthorBound(EXPECTED_NEWS_2.getId());
        assertTrue(isDelete);
    }

    @Test
    public void deleteNewsAuthorBoundFailTest() {
        boolean isDelete = newsRepository.deleteNewsAuthorBound(INIT_TEST_ID - 1);
        assertFalse(isDelete);
    }

    @Test
    public void updateNewsAuthorBoundTest() {
        boolean isUpdate = newsRepository.updateNewsAuthorBound(EXPECTED_NEWS_1.getId(), EXPECTED_AUTHOR_2.getId());
        assertTrue(isUpdate);
    }

    @Test
    public void updateNewsAuthorBoundFailWrongNewsIdTest() {
        boolean isUpdate = newsRepository.updateNewsAuthorBound(INIT_TEST_ID - 1, EXPECTED_AUTHOR_2.getId());
        assertFalse(isUpdate);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void updateNewsAuthorBoundFailWrongAuthorIdTest() {
        boolean isUpdate = newsRepository.updateNewsAuthorBound(EXPECTED_NEWS_1.getId(), INIT_TEST_ID - 1);
    }





}
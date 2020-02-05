package com.epam.lab.repository;

import com.epam.lab.configuration.TestDataSourceConfig;
import com.epam.lab.model.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.epam.lab.repository.DbTestObjects.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class})
@Sql(scripts = "classpath:db/test-init-db.sql")
public class NewsRepositoryTest {

    @Autowired
    private NewsRepository newsRepository;

    @Test
    public void createTest() {
        News testNews = new News("Test title", "Test short text", "Test full text");
        long generatedKey = newsRepository.create(testNews);
        assertEquals(INIT_SEQUENCE_ALL_ID, generatedKey);
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
                EXPECTED_NEWS_1,
                EXPECTED_NEWS_2,
                EXPECTED_NEWS_3
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
    public void findAllByTagIdTest() {
        List<News> allByTagId = newsRepository.findAllByTagId(EXPECTED_TAG_1.getId());
        assertEquals(2, allByTagId.size());
    }

    @Test
    public void findAllByTagIdFailTest() {
        List<News> allByTagId = newsRepository.findAllByTagId(INIT_TEST_ID - 1);
        assertEquals(0, allByTagId.size());
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
    public void findAuthorIdByNewsIdTest() {
        long actual = newsRepository.findAuthorIdByNewsId(EXPECTED_NEWS_2.getId());
        assertEquals(EXPECTED_AUTHOR_2.getId().longValue(), actual);
    }

    @Test()
    public void findAuthorIdByNewsIdFailTest() {
        Long actual = newsRepository.findAuthorIdByNewsId(INIT_TEST_ID - 1);
        assertNull(actual);
    }

    @Test
    public void findTagsIdesByNewsIdTest() {
        List<Long> expected = Arrays.asList(EXPECTED_TAG_1.getId(),EXPECTED_TAG_2.getId());
        List<Long> tagsId = newsRepository.findTagsIdByNewsId(EXPECTED_NEWS_1.getId());
        assertEquals(expected, tagsId);
    }

    @Test
    public void findTagsIdesByNewsIdFailTest() {
        List<Long> tagsId = newsRepository.findTagsIdByNewsId(INIT_TEST_ID - 1);
        assertEquals(Collections.emptyList(), tagsId);
    }

    @Test
    public void deleteNewsTagBoundTest() {
        boolean isDelete = newsRepository.deleteNewsTagBound(EXPECTED_NEWS_1.getId(), EXPECTED_TAG_2.getId());
        assertTrue(isDelete);
    }

    @Test
    public void deleteNewsTagBoundFailTest() {
        boolean isDelete = newsRepository.deleteNewsTagBound(EXPECTED_NEWS_1.getId(), EXPECTED_TAG_3.getId());
        assertFalse(isDelete);
    }

    @Test
    public void deleteNewsAuthorBoundTest() {
        boolean isDelete = newsRepository.deleteNewsAuthorBound(EXPECTED_NEWS_2.getId(), EXPECTED_AUTHOR_2.getId());
        assertTrue(isDelete);
    }

    @Test
    public void deleteNewsAuthorBoundFailTest() {
        boolean isDelete = newsRepository.deleteNewsAuthorBound(EXPECTED_NEWS_2.getId(), EXPECTED_AUTHOR_1.getId());
        assertFalse(isDelete);
    }

    @Test
    public void updateNewsAuthorBoundTest() {
        boolean isUpdate = newsRepository.updateNewsAuthorBound(EXPECTED_NEWS_1.getId(), EXPECTED_AUTHOR_2.getId());
        assertTrue(isUpdate);
    }

    @Test
    public void updateNewsAuthorBoundFailWrongNewsIdTest() {
        boolean isUpdate = newsRepository.updateNewsAuthorBound(4, EXPECTED_AUTHOR_2.getId());
        assertFalse(isUpdate);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void updateNewsAuthorBoundFailWrongAuthorIdTest() {
        boolean isUpdate = newsRepository.updateNewsAuthorBound(EXPECTED_NEWS_1.getId(), 4);
    }





}
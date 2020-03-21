package com.epam.lab.repository;

import com.epam.lab.configuration.AppJpaTestConfiguration;
import com.epam.lab.model.News;
import com.epam.lab.repository.specification.JpaSpecification;
import com.epam.lab.repository.specification.news.FindNewsBySearchCriteriaJpaSpecification;
import com.epam.lab.repository.specification.news.SearchCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.epam.lab.DbTestObjects.*;
import static org.junit.Assert.*;


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

    public void saveUpdateFailWrongIdTest() {
        News expected = new News(INIT_TEST_ID + 20,"Update title", "Update short text", "Update full text",
                EXPECTED_AUTHOR_2, Arrays.asList(EXPECTED_TAG_1, EXPECTED_TAG_4, EXPECTED_TAG_5));
        News actual = entityManager.find(News.class, expected.getId());
        assertNull(actual);
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

    @Test
    public void findAllBySearchCriteriaTest_1() {
        List<News> expected = Arrays.asList(EXPECTED_NEWS_5, EXPECTED_NEWS_4, EXPECTED_NEWS_7, EXPECTED_NEWS_11, EXPECTED_NEWS_10,
                EXPECTED_NEWS_1, EXPECTED_NEWS_2, EXPECTED_NEWS_8, EXPECTED_NEWS_6, EXPECTED_NEWS_9, EXPECTED_NEWS_3);
        SearchCriteria searchCriteria = new SearchCriteria(null, null, true, true);
        JpaSpecification<News> specification = new FindNewsBySearchCriteriaJpaSpecification(searchCriteria);
        List<News> actual = newsRepository.findAllBySpecification(specification);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllBySearchCriteriaTest_2() {
        List<News> expected = Arrays.asList(EXPECTED_NEWS_5, EXPECTED_NEWS_4, EXPECTED_NEWS_7, EXPECTED_NEWS_1, EXPECTED_NEWS_10,
                EXPECTED_NEWS_11, EXPECTED_NEWS_2, EXPECTED_NEWS_8, EXPECTED_NEWS_6, EXPECTED_NEWS_9, EXPECTED_NEWS_3);
        SearchCriteria searchCriteria = new SearchCriteria(null, null, true, false);
        JpaSpecification<News> specification = new FindNewsBySearchCriteriaJpaSpecification(searchCriteria);
        List<News> actual = newsRepository.findAllBySpecification(specification);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllBySearchCriteriaTest_3() {
        List<News> expected = Arrays.asList(EXPECTED_NEWS_11, EXPECTED_NEWS_3, EXPECTED_NEWS_2, EXPECTED_NEWS_4, EXPECTED_NEWS_5,
                EXPECTED_NEWS_6, EXPECTED_NEWS_8, EXPECTED_NEWS_7, EXPECTED_NEWS_9, EXPECTED_NEWS_10, EXPECTED_NEWS_1);
        SearchCriteria searchCriteria = new SearchCriteria(null, null, false, true);
        JpaSpecification<News> specification = new FindNewsBySearchCriteriaJpaSpecification(searchCriteria);
        List<News> actual = newsRepository.findAllBySpecification(specification);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllBySearchCriteriaTest_4() {
        List<News> expected = Arrays.asList(EXPECTED_NEWS_4, EXPECTED_NEWS_11, EXPECTED_NEWS_7);
        SearchCriteria searchCriteria = new SearchCriteria(null, Arrays.asList(EXPECTED_TAG_1.getId(), EXPECTED_TAG_2.getId()), false, false);
        JpaSpecification<News> specification = new FindNewsBySearchCriteriaJpaSpecification(searchCriteria);
        List<News> actual = newsRepository.findAllBySpecification(specification);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllBySearchCriteriaTest_5() {
        List<News> expected = Arrays.asList(EXPECTED_NEWS_2, EXPECTED_NEWS_8);
        SearchCriteria searchCriteria = new SearchCriteria(EXPECTED_AUTHOR_7.getId(), null, false, true);
        JpaSpecification<News> specification = new FindNewsBySearchCriteriaJpaSpecification(searchCriteria);
        List<News> actual = newsRepository.findAllBySpecification(specification);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllBySearchCriteriaTest_1_page() {
        int page = 2;
        int count = 2;
        List<News> expected = Arrays.asList(EXPECTED_NEWS_2, EXPECTED_NEWS_4);
        SearchCriteria searchCriteria = new SearchCriteria(null, null, false, true);
        JpaSpecification<News> specification = new FindNewsBySearchCriteriaJpaSpecification(searchCriteria);
        List<News> actual = newsRepository.findAllBySpecification(specification, page, count);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllBySearchCriteriaTest_2_page() {
        int page = 1;
        int count = 2;
        List<News> expected = Arrays.asList(EXPECTED_NEWS_11, EXPECTED_NEWS_3);
        SearchCriteria searchCriteria = new SearchCriteria(null, null, false, true);
        JpaSpecification<News> specification = new FindNewsBySearchCriteriaJpaSpecification(searchCriteria);
        List<News> actual = newsRepository.findAllBySpecification(specification, page, count);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllBySearchCriteriaTest_3_page() {
        int page = 1;
        int count = 3;
        List<News> expected = Arrays.asList(EXPECTED_NEWS_11, EXPECTED_NEWS_2, EXPECTED_NEWS_4);
        SearchCriteria searchCriteria = new SearchCriteria(null, Collections.singleton(EXPECTED_TAG_2.getId()), false, true);
        JpaSpecification<News> specification = new FindNewsBySearchCriteriaJpaSpecification(searchCriteria);
        List<News> actual = newsRepository.findAllBySpecification(specification, page, count);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllBySearchCriteriaTest_4_page() {
        int page = 2;
        int count = 4;
        List<News> expected = Arrays.asList(EXPECTED_NEWS_7, EXPECTED_NEWS_9, EXPECTED_NEWS_10, EXPECTED_NEWS_1);
        SearchCriteria searchCriteria = new SearchCriteria(null, Collections.singleton(EXPECTED_TAG_2.getId()), false, true);
        JpaSpecification<News> specification = new FindNewsBySearchCriteriaJpaSpecification(searchCriteria);
        List<News> actual = newsRepository.findAllBySpecification(specification, page, count);
        assertEquals(expected, actual);
    }

    @Test
    public void countBySearchCriteriaTest_1() {
        Long expected = 11L;
        SearchCriteria searchCriteria = new SearchCriteria(null, null, true, true);
        JpaSpecification<News> specification = new FindNewsBySearchCriteriaJpaSpecification(searchCriteria);
        Long actual = newsRepository.countAll(specification);
        assertEquals(expected, actual);
    }

    @Test
    public void countAllBySearchCriteriaTest_4() {
        Long expected = 3L;
        SearchCriteria searchCriteria = new SearchCriteria(null, Arrays.asList(EXPECTED_TAG_1.getId(), EXPECTED_TAG_2.getId()), false, false);
        JpaSpecification<News> specification = new FindNewsBySearchCriteriaJpaSpecification(searchCriteria);
        Long actual = newsRepository.countAll(specification);
        assertEquals(expected, actual);
    }

    @Test
    public void countBySearchCriteriaTest_5() {
        Long expected = 2L;
        SearchCriteria searchCriteria = new SearchCriteria(EXPECTED_AUTHOR_7.getId(), null, false, true);
        JpaSpecification<News> specification = new FindNewsBySearchCriteriaJpaSpecification(searchCriteria);
        Long actual = newsRepository.countAll(specification);
        assertEquals(expected, actual);
    }
}
package com.epam.lab.service;

import com.epam.lab.configuration.ServiceTestConfig;
import com.epam.lab.dto.NewsTo;
import com.epam.lab.service.impl.NewsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.epam.lab.configuration.TestObjects.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceTestConfig.class)
public class NewsServiceTest {

    @Autowired
    private NewsService newsService;

    @Test
    public void createTest() {
        NewsTo create = new NewsTo(null, CREATE_TEST_DTO_NEWS_4.getTitle(),
                CREATE_TEST_DTO_NEWS_4.getShortText(), CREATE_TEST_DTO_NEWS_4.getFullText(),
                CREATE_TEST_DTO_NEWS_4.getAuthor(),
                CREATE_TEST_DTO_NEWS_4.getTags()
        );
        NewsTo actual = newsService.create(create);
        assertEquals(CREATE_TEST_DTO_NEWS_4, actual);
    }

    @Test
    public void createFailHasIdTest() {
        NewsTo create = new NewsTo(INIT_TEST_ID, CREATE_TEST_DTO_NEWS_4.getTitle(),
                CREATE_TEST_DTO_NEWS_4.getShortText(), CREATE_TEST_DTO_NEWS_4.getFullText(),
                CREATE_TEST_DTO_NEWS_4.getAuthor(),
                CREATE_TEST_DTO_NEWS_4.getTags()
        );
        NewsTo actual = newsService.create(create);
        assertNull(actual);
    }

    @Test
    public void updateTest() {
        NewsTo newsTo = new NewsTo(UPDATE_TEST_DTO_NEWS_5.getId(), UPDATE_TEST_DTO_NEWS_5.getTitle(),
                UPDATE_TEST_DTO_NEWS_5.getShortText(), UPDATE_TEST_DTO_NEWS_5.getFullText(),
                UPDATE_TEST_DTO_NEWS_5.getAuthor(),
                UPDATE_TEST_DTO_NEWS_5.getTags()
        );
        NewsTo actual = newsService.update(newsTo);
        assertEquals(UPDATE_TEST_DTO_NEWS_5, actual);
    }

    @Test
    public void updateFailTest() {
        NewsTo newsTo = new NewsTo(INIT_TEST_ID - 1, UPDATE_TEST_DTO_NEWS_5.getTitle(),
                UPDATE_TEST_DTO_NEWS_5.getShortText(), UPDATE_TEST_DTO_NEWS_5.getFullText(),
                UPDATE_TEST_DTO_NEWS_5.getAuthor(),
                UPDATE_TEST_DTO_NEWS_5.getTags()
        );
        NewsTo actual = newsService.update(newsTo);
        assertNull(actual);
    }

    @Test
    public void deleteTest() {
        boolean isDelete = newsService.delete(EXPECTED_DTO_NEWS_1.getId());
        assertTrue(isDelete);
    }

    @Test
    public void findByIdTest() {
        NewsTo actual = newsService.findById(EXPECTED_DTO_NEWS_1.getId());
        assertEquals(EXPECTED_DTO_NEWS_1, actual);
    }

    @Test
    public void findAllTest() {
        List<NewsTo> expected = Arrays.asList(EXPECTED_DTO_NEWS_1, EXPECTED_DTO_NEWS_2, EXPECTED_DTO_NEWS_3);
        List<NewsTo> actual = newsService.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countAllTest() {
        int actual = newsService.countAll();
        assertEquals(EXPECTED_COUNT_ALL_NEWS, actual);
    }

    @Test
    public void findAllBySearchCriteriaTest() {
        List<NewsTo> actual = newsService.findAll(SEARCH_CRITERIA_1);
        List<NewsTo> expected = Collections.singletonList(EXPECTED_DTO_NEWS_1);
        assertEquals(expected, actual);
    }
}
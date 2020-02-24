package com.epam.lab.service;

import com.epam.lab.configuration.ServiceTestConfig;
import com.epam.lab.dto.AuthorTo;
import com.epam.lab.exeption.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.epam.lab.TestObjects.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceTestConfig.class)
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    public void createTest() {
        AuthorTo authorTo = new AuthorTo(null, CREATE_TEST_DTO_AUTHOR_4.getName(), CREATE_TEST_DTO_AUTHOR_4.getSurname());
        AuthorTo actual = authorService.save(authorTo);
        assertEquals(CREATE_TEST_DTO_AUTHOR_4, actual);
    }

    @Test
    public void updateTest() {
        AuthorTo authorTo = new AuthorTo(UPDATE_TEST_DTO_AUTHOR_5.getId(), UPDATE_TEST_DTO_AUTHOR_5.getName(), UPDATE_TEST_DTO_AUTHOR_5.getSurname());
        AuthorTo expected = authorService.save(authorTo);
        assertEquals(UPDATE_TEST_DTO_AUTHOR_5, expected);
    }

    @Test
    public void deleteTest() {
        boolean isDelete = authorService.delete(INIT_TEST_ID);
        assertTrue(isDelete);
    }

    @Test(expected = ServiceException.class)
    public void deleteFailWrongIdTest() {
        boolean isDelete = authorService.delete(INIT_TEST_ID - 1);
        assertFalse(isDelete);
    }

    @Test
    public void findByIdTest() {
        AuthorTo actual = authorService.findById(INIT_TEST_ID);
        assertEquals(EXPECTED_DTO_AUTHOR_1, actual);
    }

    @Test(expected = ServiceException.class)
    public void findByIdFailWrongIdTest() {
        AuthorTo actual = authorService.findById(INIT_TEST_ID - 1);
    }

    @Test
    public void findAllTest() {
        List<AuthorTo> expected = Arrays.asList(EXPECTED_DTO_AUTHOR_1, EXPECTED_DTO_AUTHOR_2, EXPECTED_DTO_AUTHOR_3);
        List<AuthorTo> actual = authorService.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countAllTest() {
        long actual = authorService.countAll();
        assertEquals(actual, EXPECTED_COUNT_ALL_AUTHORS);
    }
}
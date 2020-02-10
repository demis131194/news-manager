package com.epam.lab.service;

import com.epam.lab.configuration.ServiceTestConfig;
import com.epam.lab.dto.AuthorTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.epam.lab.configuration.TestObjects.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceTestConfig.class)
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    public void createTest() {
        AuthorTo authorTo = new AuthorTo(null, CREATE_TEST_DTO_AUTHOR_4.getName(), CREATE_TEST_DTO_AUTHOR_4.getSurname());
        AuthorTo actual = authorService.create(authorTo);
        assertEquals(CREATE_TEST_DTO_AUTHOR_4, actual);
    }

    @Test
    public void createFailHasIdTest() {
        AuthorTo authorTo = new AuthorTo(1L, CREATE_TEST_DTO_AUTHOR_4.getName(), CREATE_TEST_DTO_AUTHOR_4.getSurname());
        AuthorTo actual = authorService.create(authorTo);
        assertNull(actual);
    }

    @Test
    public void updateTest() {
        AuthorTo authorTo = new AuthorTo(INIT_TEST_ID, UPDATE_TEST_DTO_AUTHOR_5.getName(), UPDATE_TEST_DTO_AUTHOR_5.getSurname());
        AuthorTo expected = authorService.update(authorTo);
        assertEquals(UPDATE_TEST_DTO_AUTHOR_5, expected);
    }

    @Test
    public void updateFailNullIdTest() {
        AuthorTo authorTo = new AuthorTo(null, UPDATE_TEST_DTO_AUTHOR_5.getName(), UPDATE_TEST_DTO_AUTHOR_5.getSurname());
        AuthorTo expected = authorService.update(authorTo);
        assertNull(expected);
    }

    @Test
    public void deleteTest() {
        boolean isDelete = authorService.delete(INIT_TEST_ID);
        assertTrue(isDelete);
    }

    @Test
    public void deleteFailWrongIdTest() {
        boolean isDelete = authorService.delete(INIT_TEST_ID - 1);
        assertFalse(isDelete);
    }

    @Test
    public void findByIdTest() {
        AuthorTo actual = authorService.findById(INIT_TEST_ID);
        assertEquals(EXPECTED_DTO_AUTHOR_1, actual);
    }

    @Test
    public void findByIdFailWrongIdTest() {
        AuthorTo actual = authorService.findById(INIT_TEST_ID - 1);
        assertNull(actual);
    }

    @Test
    public void findAllTest() {
        List<AuthorTo> expected = Arrays.asList(EXPECTED_DTO_AUTHOR_1, EXPECTED_DTO_AUTHOR_2, EXPECTED_DTO_AUTHOR_3);
        List<AuthorTo> actual = authorService.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countAllTest() {
        int actual = authorService.countAll();
        assertEquals(actual, EXPECTED_COUNT_ALL_AUTHORS);
    }

    @Test
    public void findByNewsIdTest() {
        AuthorTo actual = authorService.findByNewsId(INIT_TEST_ID);
        assertEquals(EXPECTED_DTO_AUTHOR_1, actual);
    }

    @Test
    public void findByNewsIdFailTest() {
        AuthorTo actual = authorService.findByNewsId(INIT_TEST_ID - 1);
        assertNull(actual);
    }
}
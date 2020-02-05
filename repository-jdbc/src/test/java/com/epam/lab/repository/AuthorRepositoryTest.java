package com.epam.lab.repository;

import com.epam.lab.configuration.TestDataSourceConfig;
import com.epam.lab.model.Author;
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

import static org.junit.Assert.*;
import static com.epam.lab.repository.DbTestObjects.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class})
@Sql(scripts = "classpath:db/test-init-db.sql")
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void createTest() {
        Author testAuthor = new Author("Pavel", "Pavel");
        long generatedKey = authorRepository.create(testAuthor);
        assertEquals(INIT_SEQUENCE_ALL_ID, generatedKey);
    }

    @Test
    public void updateTest() {
        Author testAuthor = new Author(INIT_TEST_ID, "Pavel", "Pavel");
        boolean isUpdate = authorRepository.update(testAuthor);
        assertTrue(isUpdate);
    }

    @Test
    public void deleteTest() {
        boolean isDeleted = authorRepository.delete(INIT_TEST_ID);
        assertTrue(isDeleted);
    }

    @Test
    public void findByIdTest() {
        Author actual = authorRepository.findById(INIT_TEST_ID);
        assertEquals(EXPECTED_AUTHOR_1, actual);
    }

    @Test
    public void findAllTest() {
        List<Author> expected = Arrays.asList(
                EXPECTED_AUTHOR_1,
                EXPECTED_AUTHOR_2,
                EXPECTED_AUTHOR_3
        );
        List<Author> actual = authorRepository.findAll();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createFailNullFieldTest() {
        Author testAuthor = new Author("Pavel", null);
        long generatedKey = authorRepository.create(testAuthor);
    }

    @Test
    public void updateFailWrongIdTest() {
        Author testAuthor = new Author(INIT_TEST_ID - 1, "Pavel", "Pavel");
        boolean isUpdate = authorRepository.update(testAuthor);
        assertFalse(isUpdate);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void updateFailNullFieldTest() {
        Author testAuthor = new Author(INIT_TEST_ID, null, "Pavel");
        authorRepository.update(testAuthor);
    }

    @Test
    public void deleteFailWrongIdTest() {
        boolean isDeleted = authorRepository.delete(INIT_TEST_ID - 1);
        assertFalse(isDeleted);
    }

    @Test
    public void findByIdFailWrongIdTest() {
        Author actual = authorRepository.findById(INIT_TEST_ID - 1);
        assertNull(actual);
    }

    @Test
    public void findByNewsIdTest() {
        Author actual = authorRepository.findByNewsId(INIT_TEST_ID);
        assertEquals(EXPECTED_AUTHOR_1,actual);
    }

    @Test
    public void countAllTest() {
        int actual = authorRepository.countAll();
        assertEquals(EXPECTED_COUNT_ALL_AUTHORS,actual);
    }

    @Test
    public void findByNewsIdFailTest() {
        Author actual = authorRepository.findByNewsId(INIT_TEST_ID - 1);
        assertNull(actual);
    }

    @Test
    public void findByNameTest() {
        List<Author> actual = authorRepository.findByName(EXPECTED_AUTHOR_2.getName());
        assertEquals(Collections.singletonList(EXPECTED_AUTHOR_2), actual);
    }

    @Test
    public void findByNameFailTest() {
        List<Author> actual = authorRepository.findByName("No name");
        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void findBySurnameTest() {
        List<Author> actual = authorRepository.findBySurname(EXPECTED_AUTHOR_2.getSurname());
        assertEquals(Collections.singletonList(EXPECTED_AUTHOR_2), actual);
    }

    @Test
    public void findBySurnameFailTest() {
        List<Author> actual = authorRepository.findByName("No surname");
        assertEquals(Collections.emptyList(), actual);
    }



}
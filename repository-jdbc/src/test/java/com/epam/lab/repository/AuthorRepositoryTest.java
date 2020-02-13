package com.epam.lab.repository;

import com.epam.lab.configuration.TestRepositoryConfig;
import com.epam.lab.model.Author;
import com.epam.lab.repository.jdbc.AuthorRepositoryImpl;
import com.epam.lab.repository.jdbc.specification.Specification;
import com.epam.lab.repository.jdbc.specification.author.FindAuthorByNewsIdSpecification;
import com.epam.lab.repository.jdbc.specification.author.FindAuthorsByNameSpecification;
import com.epam.lab.repository.jdbc.specification.author.FindAuthorsBySurnameSpecification;
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
@ContextConfiguration(classes = {TestRepositoryConfig.class})
@Sql(scripts = "classpath:db/test-init-db.sql")
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepositoryImpl authorRepository;

    @Test
    public void createTest() {
        Author testAuthor = new Author("Pavel", "Pavel");
        long generatedKey = authorRepository.create(testAuthor);
        assertEquals(INIT_TEST_ID + EXPECTED_COUNT_ALL_AUTHORS, generatedKey);
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
                EXPECTED_AUTHOR_1, EXPECTED_AUTHOR_2, EXPECTED_AUTHOR_3, EXPECTED_AUTHOR_4,
                EXPECTED_AUTHOR_5, EXPECTED_AUTHOR_6, EXPECTED_AUTHOR_7, EXPECTED_AUTHOR_8
        );
        List<Author> actual = authorRepository.findAll();
        assertEquals(expected, actual);
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
        Specification specification = new FindAuthorByNewsIdSpecification(INIT_TEST_ID);
        List<Author> bySpecification = authorRepository.findBySpecification(specification);
        assertEquals(EXPECTED_AUTHOR_1,bySpecification.get(0));
    }

    @Test
    public void countAllTest() {
        int actual = authorRepository.countAll();
        assertEquals(EXPECTED_COUNT_ALL_AUTHORS,actual);
    }

    @Test
    public void findByNewsIdFailTest() {
        Specification specification = new FindAuthorByNewsIdSpecification(INIT_TEST_ID - 1);
        List<Author> actual = authorRepository.findBySpecification(specification);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void findByNameTest() {
        Specification specification = new FindAuthorsByNameSpecification(EXPECTED_AUTHOR_1.getName());
        List<Author> expected = Arrays.asList(EXPECTED_AUTHOR_1, EXPECTED_AUTHOR_7, EXPECTED_AUTHOR_8);
        List<Author> actual = authorRepository.findBySpecification(specification);
        assertEquals(expected, actual);
    }

    @Test
    public void findByNameFailTest() {
        Specification specification = new FindAuthorsByNameSpecification("No name");
        List<Author> actual = authorRepository.findBySpecification(specification);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void findBySurnameTest() {
        Specification specification = new FindAuthorsBySurnameSpecification(EXPECTED_AUTHOR_2.getSurname());
        List<Author> actual = authorRepository.findBySpecification(specification);
        assertEquals(Collections.singletonList(EXPECTED_AUTHOR_2), actual);
    }

    @Test
    public void findBySurnameFailTest() {
        Specification specification = new FindAuthorsBySurnameSpecification("No surname");
        List<Author> actual = authorRepository.findBySpecification(specification);
        assertTrue(actual.isEmpty());
    }



}
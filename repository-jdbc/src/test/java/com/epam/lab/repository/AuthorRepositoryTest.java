package com.epam.lab.repository;

import com.epam.lab.configuration.TestDataSourceConfig;
import com.epam.lab.model.Author;
import org.junit.Assert;
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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class})
@Sql(scripts = "classpath:db/test-init-db.sql")
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    private final long INIT_KEY_ID = 10000L;
    @Test
    public void createTest() {
        Author testAuthor = new Author("Pavel", "Pavel");
        long generatedKey = authorRepository.create(testAuthor);
        Assert.assertNotEquals(0, generatedKey);
    }

    @Test
    public void updateTest() {
        Author testAuthor = new Author(INIT_KEY_ID, "Pavel", "Pavel");
        boolean isUpdate = authorRepository.update(testAuthor);
        Assert.assertTrue(isUpdate);
    }

    @Test
    public void deleteTest() {
        boolean isDeleted = authorRepository.delete(INIT_KEY_ID);
        Assert.assertTrue(isDeleted);
    }

    @Test
    public void findByIdTest() {
        long id = INIT_KEY_ID;
        Author expected = new Author(id, "DIMA", "FORD");
        Author actual = authorRepository.findById(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findAllTest() {
        long startKey = INIT_KEY_ID;
        List<Author> expected = Arrays.asList(
                new Author(startKey, "DIMA", "FORD"),
                new Author(startKey + 1, "VASYA", "VASYA"),
                new Author(startKey + 2, "SOVA", "SOVA")
        );
        List<Author> actual = authorRepository.findAll();
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createFailNullFieldTest() {
        Author testAuthor = new Author("Pavel", null);
        long generatedKey = authorRepository.create(testAuthor);
        Assert.assertNotEquals(0, generatedKey);
    }

    @Test
    public void updateFailWrongIdTest() {
        Author testAuthor = new Author(0, "Pavel", "Pavel");
        boolean isUpdate = authorRepository.update(testAuthor);
        Assert.assertFalse(isUpdate);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void updateFailNullFieldTest() {
        Author testAuthor = new Author(INIT_KEY_ID, null, "Pavel");
        authorRepository.update(testAuthor);
    }

    @Test
    public void deleteFailWrongIdTest() {
        boolean isDeleted = authorRepository.delete(INIT_KEY_ID - 1);
        Assert.assertFalse(isDeleted);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findByIdFailWrongIdTest() {
        Author actual = authorRepository.findById(INIT_KEY_ID - 1);
    }
}
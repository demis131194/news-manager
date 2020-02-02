package com.epam.lab.repository;

import com.epam.lab.configuration.TestDataSourceConfig;
import com.epam.lab.model.Tag;
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
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    private final long INIT_KEY_ID = 1L;

    @Test
    public void createTest() {
        Tag testTag = new Tag("Test");
        long generatedKey = tagRepository.create(testTag);
        assertNotEquals(0, generatedKey);
    }

    @Test
    public void updateTest() {
        Tag testTag = new Tag(INIT_KEY_ID, "Update");
        boolean isUpdate = tagRepository.update(testTag);
        assertTrue(isUpdate);
    }

    @Test
    public void deleteTest() {
        boolean isDeleted = tagRepository.delete(INIT_KEY_ID);
        assertTrue(isDeleted);
    }

    @Test
    public void findByIdTest() {
        long id = INIT_KEY_ID;
        Tag expected = new Tag(id, "History");
        Tag actual = tagRepository.findById(id);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllTest() {
        long startKey = INIT_KEY_ID;
        List<Tag> expected = Arrays.asList(
                new Tag(startKey, "History"),
                new Tag(startKey + 1, "SCIENCE"),
                new Tag(startKey + 2, "FANNY")
        );
        List<Tag> actual = tagRepository.findAll();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createFailNullFieldTest() {
        Tag testTag = new Tag(null);
        long generatedKey = tagRepository.create(testTag);
    }

    @Test
    public void updateFailWrongIdTest() {
        Tag testTag = new Tag(0L, "Test update");
        boolean isUpdate = tagRepository.update(testTag);
        assertFalse(isUpdate);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void updateFailNullFieldTest() {
        Tag testTag = new Tag(INIT_KEY_ID, null);
        tagRepository.update(testTag);
    }

    @Test
    public void deleteFailWrongIdTest() {
        boolean isDeleted = tagRepository.delete(INIT_KEY_ID - 1);
        assertFalse(isDeleted);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findByIdFailWrongIdTest() {
        Tag actual = tagRepository.findById(INIT_KEY_ID - 1);
    }

    @Test
    public void findTagsByNewsIdTest() {
        List<Tag> expectedTags = Arrays.asList(new Tag(1L, "History"),
                new Tag(2L, "SCIENCE"));
        System.out.println(expectedTags);
        List<Tag> actualTags = tagRepository.findTagsByNewsId(1);
        System.out.println(actualTags);
        assertArrayEquals(expectedTags.toArray(), actualTags.toArray());
    }
}
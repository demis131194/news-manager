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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class})
@Sql(scripts = "classpath:db/test-init-db.sql")
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    private static final long INIT_KEY_ID = 1L;

    private static final Tag EXPECTED_TAG_1 = new Tag(INIT_KEY_ID, "History");
    private static final Tag EXPECTED_TAG_2 = new Tag(INIT_KEY_ID + 1, "SCIENCE");
    private static final Tag EXPECTED_TAG_3 = new Tag(INIT_KEY_ID + 2, "FANNY");


    @Test
    public void createTest() {
        Tag testTag = new Tag("Test");
        long expectedIdKey = 10000;
        long generatedIdKey = tagRepository.create(testTag);
        assertEquals(expectedIdKey, generatedIdKey);
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
        Tag actual = tagRepository.findById(id);
        assertEquals(EXPECTED_TAG_1, actual);
    }

    @Test
    public void findAllTest() {
        long startKey = INIT_KEY_ID;
        List<Tag> expected = Arrays.asList(
                EXPECTED_TAG_1,
                EXPECTED_TAG_2,
                EXPECTED_TAG_3
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
        Tag testTag = new Tag(INIT_KEY_ID - 1, "Test update");
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
        boolean isDeleted = tagRepository.delete(INIT_KEY_ID + 3);
        assertFalse(isDeleted);
    }

    @Test
    public void findByIdFailWrongIdTest() {
        Tag expected = null;
        Tag actual = tagRepository.findById(INIT_KEY_ID + 3);
        assertEquals(expected, actual);
    }

    @Test
    public void findTagsByNewsIdTest() {
        Set<Tag> expectedTags = new HashSet<>(
                Arrays.asList(
                        EXPECTED_TAG_1,
                        EXPECTED_TAG_2
                )
        );
        Set<Tag> actualTags = tagRepository.findTagsByNewsId(1);
        assertArrayEquals(expectedTags.toArray(), actualTags.toArray());
    }

    @Test
    public void countAllTest() {
        int expected = 3;
        int actual = tagRepository.countAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findTagByNameTest() {
        Tag actualTag = tagRepository.findTagByName(EXPECTED_TAG_2.getName());
        assertEquals(EXPECTED_TAG_2, actualTag);
    }

    @Test
    public void findTagByNameFailTest() {
        Tag expected = null;
        Tag actualTag = tagRepository.findTagByName("Undefine tag");
        assertEquals(expected, actualTag);
    }

}
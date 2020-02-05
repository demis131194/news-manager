package com.epam.lab.repository;

import com.epam.lab.configuration.TestRepositoryConfig;
import com.epam.lab.model.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static com.epam.lab.repository.DbTestObjects.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestRepositoryConfig.class})
@Sql(scripts = "classpath:db/test-init-db.sql")
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void createTest() {
        Tag createTestTag = new Tag("Test");
        long generatedIdKey = tagRepository.create(createTestTag);
        assertEquals(INIT_SEQUENCE_ALL_ID, generatedIdKey);
    }

    @Test
    public void updateTest() {
        Tag updateTestTag = new Tag(INIT_TEST_ID, "Update");
        boolean isUpdate = tagRepository.update(updateTestTag);
        assertTrue(isUpdate);
    }

    @Test
    public void deleteTest() {
        boolean isDeleted = tagRepository.delete(INIT_TEST_ID);
        assertTrue(isDeleted);
    }

    @Test
    public void findByIdTest() {
        long id = INIT_TEST_ID;
        Tag actual = tagRepository.findById(id);
        assertEquals(EXPECTED_TAG_1, actual);
    }

    @Test
    public void findAllTest() {
        Set<Tag> expected = new HashSet<>(
                Arrays.asList(
                        EXPECTED_TAG_1,
                        EXPECTED_TAG_2,
                        EXPECTED_TAG_3
                )
        );
        Set<Tag> actual = tagRepository.findAll();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createFailNullFieldTest() {
        Tag testTag = new Tag(null);
        long generatedKey = tagRepository.create(testTag);
    }

    @Test
    public void updateFailWrongIdTest() {
        Tag testTag = new Tag(INIT_TEST_ID - 1, "Test update");
        boolean isUpdate = tagRepository.update(testTag);
        assertFalse(isUpdate);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void updateFailNullFieldTest() {
        Tag testTag = new Tag(INIT_TEST_ID, null);
        tagRepository.update(testTag);
    }

    @Test
    public void deleteFailWrongIdTest() {
        boolean isDeleted = tagRepository.delete(INIT_TEST_ID - 1);
        assertFalse(isDeleted);
    }

    @Test
    public void findByIdFailWrongIdTest() {
        Tag actual = tagRepository.findById(INIT_TEST_ID - 1);
        assertNull(actual);
    }

    @Test
    public void findTagsByNewsIdTest() {
        Set<Tag> expectedTags = new HashSet<>(
                Arrays.asList(
                        EXPECTED_TAG_1,
                        EXPECTED_TAG_2
                )
        );
        Set<Tag> actualTags = tagRepository.findTagsByNewsId(INIT_TEST_ID);
        assertArrayEquals(expectedTags.toArray(), actualTags.toArray());
    }

    @Test
    public void countAllTest() {
        int actual = tagRepository.countAll();
        assertEquals(EXPECTED_COUNT_ALL_TAGS, actual);
    }

    @Test
    public void findTagByNameTest() {
        Tag actualTag = tagRepository.findTagByName(EXPECTED_TAG_2.getName());
        assertEquals(EXPECTED_TAG_2, actualTag);
    }

    @Test
    public void findTagByNameFailTest() {
        Tag actualTag = tagRepository.findTagByName("Undefine tag");
        assertNull(actualTag);
    }

}
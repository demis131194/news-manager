package com.epam.lab.repository;

import com.epam.lab.configuration.TestRepositoryConfig;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.jdbc.TagRepositoryImpl;
import com.epam.lab.repository.jdbc.specification.Specification;
import com.epam.lab.repository.jdbc.specification.tag.FindAllTagsSpecification;
import com.epam.lab.repository.jdbc.specification.tag.FindTagByIdSpecification;
import com.epam.lab.repository.jdbc.specification.tag.FindTagByNameSpecification;
import com.epam.lab.repository.jdbc.specification.tag.FindTagsByNewsIdSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.epam.lab.repository.DbTestObjects.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestRepositoryConfig.class})
@Sql(scripts = "classpath:db/test-init-db.sql")
public class TagRepositoryTest {

    @Autowired
    private TagRepositoryImpl tagRepository;

    @Test
    public void createTest() {
        Tag createTestTag = new Tag("Test");
        long generatedIdKey = tagRepository.create(createTestTag);
        assertEquals(INIT_TEST_ID + EXPECTED_COUNT_ALL_TAGS, generatedIdKey);
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
        Specification specification = new FindTagByIdSpecification(INIT_TEST_ID);
        List<Tag> result = tagRepository.findBySpecification(specification);
        Tag actual = result.isEmpty() ? null : result.get(0);
        assertEquals(EXPECTED_TAG_1, actual);
    }

    @Test
    public void findAllTest() {
        List<Tag> expected = Arrays.asList(
                EXPECTED_TAG_1, EXPECTED_TAG_2, EXPECTED_TAG_3, EXPECTED_TAG_4,
                EXPECTED_TAG_5, EXPECTED_TAG_6, EXPECTED_TAG_7, EXPECTED_TAG_8
        );
        Specification specification = new FindAllTagsSpecification();
        List<Tag> actual = tagRepository.findBySpecification(specification);
        assertEquals(expected, actual);
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
        Specification specification = new FindTagByIdSpecification(INIT_TEST_ID - 1);
        List<Tag> result = tagRepository.findBySpecification(specification);
        Tag actual = result.isEmpty() ? null : result.get(0);
        assertNull(actual);
    }

    @Test
    public void findTagsByNewsIdTest() {
        List<Tag> expectedTags = Arrays.asList(
                        EXPECTED_TAG_2,
                        EXPECTED_TAG_3,
                        EXPECTED_TAG_6
        );
        Specification specification = new FindTagsByNewsIdSpecification(INIT_TEST_ID);
        List<Tag> actualTags = tagRepository.findBySpecification(specification);
        assertEquals(expectedTags, actualTags);
    }

    @Test
    public void countAllTest() {
        long actual = tagRepository.countAll();
        assertEquals(EXPECTED_COUNT_ALL_TAGS, actual);
    }

    @Test
    public void findTagByNameTest() {
        Specification specification = new FindTagByNameSpecification(EXPECTED_TAG_2.getName());
        Tag actualTag = tagRepository.findBySpecification(specification).get(0);
        assertEquals(EXPECTED_TAG_2, actualTag);
    }

    @Test
    public void findTagByNameFailTest() {
        Specification specification = new FindTagByNameSpecification("Undefine tag");
        List<Tag> tags = tagRepository.findBySpecification(specification);
        assertTrue(tags.isEmpty());
    }

}
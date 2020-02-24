package com.epam.lab.repository;

import com.epam.lab.configuration.AppJpaTestConfiguration;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.specification.JpaSpecification;
import com.epam.lab.repository.specification.tag.FindTagByNameJpaSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.epam.lab.DbTestObjects.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppJpaTestConfiguration.class)
@Sql(scripts = "classpath:db/test-init-db.sql")
public class JpaTagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void saveCreateTest() {
        Tag created = new Tag("Create Tag");
        Tag expected = tagRepository.save(created);
        Tag actual = entityManager.find(Tag.class, expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void saveUpdateTest() {
        Tag expected = new Tag(EXPECTED_TAG_1.getId(),"Update Tag");
        Tag saved = tagRepository.save(expected);
        Tag actual = entityManager.find(Tag.class, expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void saveUpdateFailWrongIdTest() {
        Tag expected = new Tag(INIT_TEST_ID + 20,"Update Tag");
        Tag actual = entityManager.find(Tag.class, expected.getId());
        assertNull(actual);
    }

    @Test
    public void deleteTest() {
        boolean isDeleted = tagRepository.delete(EXPECTED_TAG_1.getId());
        assertTrue(isDeleted);
    }

    @Test
    public void deleteFailTest() {
        boolean isDeleted = tagRepository.delete(EXPECTED_COUNT_ALL_TAGS + 1L);
        assertFalse(isDeleted);
    }

    @Test
    public void findTagByIdTest() {
        Tag actual = tagRepository.findById(EXPECTED_TAG_1.getId());
        assertEquals(EXPECTED_TAG_1, actual);
    }

    @Test
    public void findAllTagsTest() {
        List<Tag> expected = Arrays.asList(EXPECTED_TAG_1, EXPECTED_TAG_2, EXPECTED_TAG_3, EXPECTED_TAG_4,
                EXPECTED_TAG_5, EXPECTED_TAG_6, EXPECTED_TAG_7, EXPECTED_TAG_8);
        List<Tag> actual = tagRepository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void countAllTest() {
        long actual = tagRepository.countAll();
        assertEquals(EXPECTED_COUNT_ALL_TAGS, actual);
    }

    @Test
    public void findTagByNameTest() {
        String tagName = EXPECTED_TAG_5.getName();
        JpaSpecification<Tag> specification = new FindTagByNameJpaSpecification(tagName);
        List<Tag> actual = tagRepository.findAllBySpecification(specification);
        List<Tag> expected = Collections.singletonList(EXPECTED_TAG_5);
        assertEquals(expected, actual);
    }
}
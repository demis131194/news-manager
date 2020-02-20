package com.epam.lab.repository;

import com.epam.lab.configuration.AppJpaTestConfiguration;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.jpa.specification.JpaSpecification;
import com.epam.lab.repository.jpa.specification.tag.FindTagByIdJpaSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
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
        Tag actual = entityManager.createQuery("SELECT t from Tag t WHERE t.id=:id", Tag.class)
                .setParameter("id", expected.getId())
                .getSingleResult();
        assertEquals(expected, actual);
    }

    @Test
    public void saveUpdateTest() {
        Tag expected = new Tag(EXPECTED_TAG_1.getId(),"Update Tag");
        tagRepository.save(expected);
        Tag actual = entityManager.createQuery("SELECT t from Tag t WHERE t.id=:id", Tag.class)
                .setParameter("id", expected.getId())
                .getSingleResult();
        assertEquals(expected, actual);
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
    public void findTagByIdSpecificationTest() {
        JpaSpecification<Tag> specification = new FindTagByIdJpaSpecification(EXPECTED_TAG_1.getId());
        Tag actual = tagRepository.findBySpecification(specification);
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
}
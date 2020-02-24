package com.epam.lab.repository;

import com.epam.lab.configuration.AppJpaTestConfiguration;
import com.epam.lab.model.Author;
import com.epam.lab.repository.specification.JpaSpecification;
import com.epam.lab.repository.specification.author.FindAuthorsByNameJpaSpecification;
import com.epam.lab.repository.specification.author.FindAuthorsBySurnameJpaSpecification;
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
public class JpaAuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void saveCreateTest() {
        Author created = new Author("Create author name", "Create author surname");
        Author expected = authorRepository.save(created);
        Author actual = entityManager.find(Author.class, expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void saveUpdateTest() {
        Author expected = new Author(EXPECTED_AUTHOR_1.getId(),"Update author name", "Update author surname");
        authorRepository.save(expected);
        Author actual = entityManager.find(Author.class, expected.getId());
        assertEquals(expected, actual);
    }

    public void saveUpdateFailWrongIdTest() {
        Author expected = new Author(INIT_TEST_ID + 20,"Update name", "Update Surname");
        Author actual = entityManager.find(Author.class, expected.getId());
        assertNull(actual);
    }

    @Test
    public void deleteTest() {
        boolean isDeleted = authorRepository.delete(EXPECTED_AUTHOR_1.getId());
        assertTrue(isDeleted);
    }

    @Test
    public void findAuthorByIdTest() {
        Author actual = authorRepository.findById(EXPECTED_AUTHOR_1.getId());
        assertEquals(EXPECTED_AUTHOR_1, actual);
    }

    @Test
    public void findAll() {
        List<Author> expected = Arrays.asList(EXPECTED_AUTHOR_1, EXPECTED_AUTHOR_2, EXPECTED_AUTHOR_3, EXPECTED_AUTHOR_4,
                EXPECTED_AUTHOR_5, EXPECTED_AUTHOR_6, EXPECTED_AUTHOR_7, EXPECTED_AUTHOR_8);
        List<Author> actual = authorRepository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findAllByNameTest() {
        List<Author> expected = Arrays.asList(EXPECTED_AUTHOR_1, EXPECTED_AUTHOR_7, EXPECTED_AUTHOR_8);
        JpaSpecification<Author> specification = new FindAuthorsByNameJpaSpecification(EXPECTED_AUTHOR_1.getName());
        List<Author> actual = authorRepository.findAllBySpecification(specification);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllBySurnameTest() {
        List<Author> expected = Arrays.asList(EXPECTED_AUTHOR_1, EXPECTED_AUTHOR_4, EXPECTED_AUTHOR_7);
        JpaSpecification<Author> specification = new FindAuthorsBySurnameJpaSpecification(EXPECTED_AUTHOR_1.getSurname());
        List<Author> actual = authorRepository.findAllBySpecification(specification);
        assertEquals(expected, actual);
    }

    @Test
    public void countAll() {
        long actual = authorRepository.countAll();
        assertEquals(EXPECTED_COUNT_ALL_AUTHORS, actual);
    }
}
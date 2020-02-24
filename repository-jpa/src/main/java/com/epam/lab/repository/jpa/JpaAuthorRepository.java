package com.epam.lab.repository.jpa;

import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.DbConstants;
import com.epam.lab.repository.specification.JpaSpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaAuthorRepository implements AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Author save(Author author) {
        if (author.isNew()) {
            entityManager.persist(author);
        } else {
            entityManager.merge(author);
        }
        return entityManager.find(Author.class, author.getId());
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        return entityManager.createNamedQuery(Author.DELETE)
                .setParameter(DbConstants.ID_COLUMN_NAME, id)
                .executeUpdate() != 0;
    }

    @Override
    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public List<Author> findAllBySpecification(JpaSpecification<Author> specification) {
        return specification.query(entityManager).getResultList();
    }

    @Override
    public List<Author> findAll() {
        return entityManager.createNamedQuery(Author.FIND_ALL, Author.class).getResultList();
    }

    @Override
    public long countAll() {
        return entityManager.createNamedQuery(Author.COUNT_ALL, Long.class).getSingleResult();
    }
}

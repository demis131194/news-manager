package com.epam.lab.repository.jpa;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.DbConstants;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.repository.jpa.specification.JpaSpecification;
import com.epam.lab.repository.jpa.specification.tag.FindAllTagsJpaSpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaTagRepository implements TagRepository {

    private static final FindAllTagsJpaSpecification findAll = new FindAllTagsJpaSpecification();

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Tag save(Tag tag) {
        if (tag.isNew()) {
            entityManager.persist(tag);
        } else {
            entityManager.merge(tag);
        }
        return tag;
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        return entityManager.createNamedQuery(Tag.DELETE)
                .setParameter(DbConstants.ID_COLUMN_NAME, id)
                .executeUpdate() != 0;
    }

    @Override
    public List<Tag> findBySpecification(JpaSpecification<Tag> specification) {
        return specification.query(entityManager).getResultList();

    }

    @Override
    public long countAll() {
        return entityManager.createNamedQuery(Tag.COUNT_ALL, Long.class).getSingleResult();
    }
}

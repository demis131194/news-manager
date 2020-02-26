package com.epam.lab.repository.jpa;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.DbConstants;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.repository.specification.JpaSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaTagRepository implements TagRepository {

    private static final Logger logger = LoggerFactory.getLogger(JpaTagRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Tag save(Tag tag) {
        logger.trace("Start JpaTagRepository save method");
        if (tag.isNew()) {
            logger.debug("persist tag - {}", tag);
            entityManager.persist(tag);
        } else {
            logger.debug("merge tag - {}", tag);
            entityManager.merge(tag);
        }
        Tag findTag = entityManager.find(Tag.class, tag.getId());
        logger.info("Saved tag - {}", findTag);
        logger.trace("End JpaTagRepository save method");
        return findTag;
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        logger.trace("Start JpaTagRepository delete method");
        logger.debug("Deleted by id - {}", id);
        boolean isDelete = entityManager.createNamedQuery(Tag.DELETE)
                .setParameter(DbConstants.ID_COLUMN_NAME, id)
                .executeUpdate() != 0;
        logger.info("Result of delete tag by id {} - {}", id, isDelete);
        logger.trace("End JpaTagRepository delete method");
        return isDelete;
    }

    @Override
    public Tag findById(long id) {
        logger.trace("Start JpaTagRepository findById method");
        logger.debug("Try find tag by id - {}", id);
        Tag tag = entityManager.find(Tag.class, id);
        logger.info("Find tag by id {} - {}", id, tag);
        logger.trace("End JpaTagRepository findById method");
        return tag;
    }

    @Override
    public List<Tag> findAllBySpecification(JpaSpecification<Tag> specification) {
        logger.trace("Start JpaTagRepository findAllBySpecification method");
        logger.debug("Try find tags by specification - {}", specification.getClass().getSimpleName());
        List<Tag> resultList = specification.query(entityManager).getResultList();
        logger.info("Find tags - {}", resultList);
        logger.trace("End JpaTagRepository findAllBySpecification method");
        return resultList;
    }

    @Override
    public List<Tag> findAll() {
        logger.trace("Start JpaTagRepository findAll method");
        logger.debug("Try find all tags");
        List<Tag> resultList = entityManager.createNamedQuery(Tag.FIND_ALL, Tag.class).getResultList();
        logger.info("Find tags - {}", resultList);
        logger.trace("End JpaTagRepository findAll method");
        return resultList;
    }

    @Override
    public long countAll() {
        logger.trace("Start JpaTagRepository countAll method");
        logger.debug("Try count all tags");
        long result = entityManager.createNamedQuery(Tag.COUNT_ALL, Long.class).getSingleResult();
        logger.info("Tags count - {}", result);
        logger.trace("End JpaTagRepository countAll method");
        return result;
    }
}

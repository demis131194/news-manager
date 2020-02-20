package com.epam.lab.repository.jpa.specification.tag;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.DbConstants;
import com.epam.lab.repository.jpa.specification.JpaTagSpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

public class FindTagByIdJpaSpecification implements JpaTagSpecification {
    private long tagId;

    public FindTagByIdJpaSpecification(long tagId) {
        this.tagId = tagId;
    }

    @Override
    public TypedQuery<Tag> query(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> tagRoot = criteriaQuery.from(Tag.class);
        ParameterExpression<Long> parameterExpression = criteriaBuilder.parameter(Long.class, DbConstants.ID_COLUMN_NAME);
        Predicate equal = criteriaBuilder.equal(tagRoot.get(DbConstants.ID_COLUMN_NAME), parameterExpression);
        criteriaQuery.select(tagRoot).where(equal);
        return entityManager.createQuery(criteriaQuery).setParameter(DbConstants.ID_COLUMN_NAME, tagId);
    }
}

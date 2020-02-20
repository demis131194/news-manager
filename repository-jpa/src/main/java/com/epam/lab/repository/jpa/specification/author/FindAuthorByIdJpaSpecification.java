package com.epam.lab.repository.jpa.specification.author;

import com.epam.lab.model.Author;
import com.epam.lab.repository.jpa.specification.JpaSpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import static com.epam.lab.repository.DbConstants.ID_COLUMN_NAME;

public class FindAuthorByIdJpaSpecification implements JpaSpecification<Author> {
    private long authorId;

    public FindAuthorByIdJpaSpecification(long authorId) {
        this.authorId = authorId;
    }

    @Override
    public TypedQuery<Author> query(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> authorRoot = criteriaQuery.from(Author.class);
        ParameterExpression<Long> idParameterExpression = criteriaBuilder.parameter(Long.class, ID_COLUMN_NAME);
        Predicate equalAuthorId = criteriaBuilder.equal(authorRoot.get(ID_COLUMN_NAME), idParameterExpression);
        criteriaQuery.select(authorRoot).where(equalAuthorId);
        return entityManager.createQuery(criteriaQuery).setParameter(ID_COLUMN_NAME, authorId);
    }
}

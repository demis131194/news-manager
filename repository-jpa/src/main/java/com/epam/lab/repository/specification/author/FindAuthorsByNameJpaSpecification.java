package com.epam.lab.repository.specification.author;

import com.epam.lab.model.Author;
import com.epam.lab.repository.specification.JpaSpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import static com.epam.lab.repository.DbConstants.AUTHORS_NAME_COLUMN_NAME;

public class FindAuthorsByNameJpaSpecification implements JpaSpecification<Author> {
    private String authorName;

    public FindAuthorsByNameJpaSpecification(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    @Override
    public TypedQuery<Author> query(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> authorRoot = criteriaQuery.from(Author.class);
        ParameterExpression<String> nameParameterExpression = criteriaBuilder.parameter(String.class, AUTHORS_NAME_COLUMN_NAME);
        Predicate equalAuthorName = criteriaBuilder.equal(authorRoot.get(AUTHORS_NAME_COLUMN_NAME), nameParameterExpression);
        criteriaQuery.select(authorRoot).where(equalAuthorName);
        return entityManager.createQuery(criteriaQuery).setParameter(AUTHORS_NAME_COLUMN_NAME, authorName);
    }
}

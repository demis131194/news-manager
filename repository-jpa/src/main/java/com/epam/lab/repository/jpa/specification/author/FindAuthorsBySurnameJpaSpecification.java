package com.epam.lab.repository.jpa.specification.author;

import com.epam.lab.model.Author;
import com.epam.lab.repository.jpa.specification.JpaSpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import static com.epam.lab.repository.DbConstants.AUTHORS_SURNAME_COLUMN_NAME;

public class FindAuthorsBySurnameJpaSpecification implements JpaSpecification<Author> {
    private String authorSurname;

    public FindAuthorsBySurnameJpaSpecification(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    @Override
    public TypedQuery<Author> query(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> authorRoot = criteriaQuery.from(Author.class);
        ParameterExpression<String> surnameParameterExpression = criteriaBuilder.parameter(String.class, AUTHORS_SURNAME_COLUMN_NAME);
        Predicate equalAuthorName = criteriaBuilder.equal(authorRoot.get(AUTHORS_SURNAME_COLUMN_NAME), surnameParameterExpression);
        criteriaQuery.select(authorRoot).where(equalAuthorName);
        return entityManager.createQuery(criteriaQuery).setParameter(AUTHORS_SURNAME_COLUMN_NAME, authorSurname);
    }
}

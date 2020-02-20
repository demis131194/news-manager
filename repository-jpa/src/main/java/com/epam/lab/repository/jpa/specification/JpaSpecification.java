package com.epam.lab.repository.jpa.specification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public interface JpaSpecification<T> {
    TypedQuery<T> query(EntityManager entityManager);
}

package com.epam.lab.repository.jpa.specification;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public interface JpaSpecification<T> {
    Query query(EntityManager entityManager);
}

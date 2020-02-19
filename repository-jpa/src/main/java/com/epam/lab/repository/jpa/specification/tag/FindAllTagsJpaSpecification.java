package com.epam.lab.repository.jpa.specification.tag;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.jpa.specification.JpaSpecification;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class FindAllTagsJpaSpecification implements JpaSpecification<Tag> {                         // FIXME: 2/19/2020 REFACTOR!!!! make static constant??

    @Override
    public Query query(EntityManager entityManager) {
        return entityManager.createNamedQuery(Tag.FIND_ALL);
    }
}

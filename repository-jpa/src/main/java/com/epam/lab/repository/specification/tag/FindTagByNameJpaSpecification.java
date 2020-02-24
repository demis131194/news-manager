package com.epam.lab.repository.specification.tag;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.specification.JpaSpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class FindTagByNameJpaSpecification implements JpaSpecification<Tag> {
    private String tagName;

    public FindTagByNameJpaSpecification(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public TypedQuery<Tag> query(EntityManager entityManager) {
        return entityManager.createNamedQuery(Tag.FIND_BY_NAME, Tag.class).setParameter(1, tagName);
    }
}

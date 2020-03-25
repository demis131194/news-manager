package com.epam.lab.repository.specification.news;

import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.specification.JpaSpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.lab.repository.DbConstants.ID_COLUMN_NAME;

public class FindNewsBySearchCriteriaJpaSpecification implements JpaSpecification<News> {

    private SearchCriteria searchCriteria;

    public FindNewsBySearchCriteriaJpaSpecification(SearchCriteria searchCriteria) {            // FIXME: 2/21/2020 Refactor
        this.searchCriteria = searchCriteria;
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    @Override
    public TypedQuery<News> query(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> newsCriteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> newsRoot = newsCriteriaQuery.from(News.class);

        newsCriteriaQuery.select(newsRoot);

        Predicate allPredicatesForWhere = criteriaBuilder.and();
        List<Order> orderList = new ArrayList<>();

        List<Expression<?>> groupExpressionsList = new ArrayList<>();
        groupExpressionsList.add(newsRoot.get(ID_COLUMN_NAME));
        groupExpressionsList.add(newsRoot.get("author"));


        if (searchCriteria.getAuthorId() != null) {
            Predicate equal = criteriaBuilder.equal(newsRoot.get("author").get("id"), searchCriteria.getAuthorId());
            allPredicatesForWhere.getExpressions().add(equal);

        }
        if (searchCriteria.getTagsId() != null && searchCriteria.getTagsId().size() > 0) {
            Join<News, Tag> tagJoin = newsRoot.join("tags");
            Predicate inPredicate = tagJoin.get(ID_COLUMN_NAME).in(searchCriteria.getTagsId());
            Expression<Long> countTagId = criteriaBuilder.count(tagJoin.get(ID_COLUMN_NAME));

            allPredicatesForWhere.getExpressions().add(inPredicate);

            newsCriteriaQuery.having(criteriaBuilder.equal(countTagId, searchCriteria.getTagsId().size()));
        }
        if (searchCriteria.isAuthorSort()) {
            orderList.add(criteriaBuilder.asc(newsRoot.get("author").get("surname")));
            orderList.add(criteriaBuilder.asc(newsRoot.get("author").get("name")));
            orderList.add(criteriaBuilder.asc(newsRoot.get("author").get("id")));

            groupExpressionsList.add(newsRoot.get("author").get("surname"));
            groupExpressionsList.add(newsRoot.get("author").get("name"));
            groupExpressionsList.add(newsRoot.get("author").get("id"));
        }
        if (searchCriteria.isCreateDateSort()) {
            orderList.add(criteriaBuilder.desc(newsRoot.get("creationDate")));
        }

        newsCriteriaQuery.where(allPredicatesForWhere).groupBy(groupExpressionsList).orderBy(orderList);

        return entityManager.createQuery(newsCriteriaQuery);
    }
}

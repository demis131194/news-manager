package com.epam.lab.repository.specification.news;

import com.epam.lab.repository.specification.Specification;
import com.healthmarketscience.sqlbuilder.*;

import java.util.Collection;
import java.util.Objects;

import static com.epam.lab.repository.DbInfo.*;

public class FindNewsBySearchCriteriaSpecification implements Specification {

    private SearchCriteria searchCriteria;

    public FindNewsBySearchCriteriaSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public String query() {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllTableColumns(NEWS_TABLE).addJoins(SelectQuery.JoinType.LEFT_OUTER,
                NEWS_AUTHORS_NEWS_JOIN, NEWS_AUTHORS_AUTHORS_JOIN, NEWS_TAGS_NEWS_JOIN, NEWS_TAGS_TAGS_JOIN);

        addAuthorConditionIfExist(selectQuery, searchCriteria.getAuthorId());
        addTagsConditionsIfExist(selectQuery, searchCriteria.getTagsId());
        addAuthorSort(selectQuery, searchCriteria.isAuthorSort());
        addNewsCreationDateSort(selectQuery, searchCriteria.isCreateDateSort());

        selectQuery.addGroupings(NEWS_ID_COLUMN);
        return selectQuery.validate().toString();
    }

    private void addAuthorConditionIfExist(SelectQuery selectQuery, Long authorId) {
        if (authorId != null) {
            selectQuery.addCondition(BinaryCondition.equalTo(AUTHORS_ID_COLUMN, authorId));
        }
    }

    private void addTagsConditionsIfExist(SelectQuery selectQuery, Collection<Long> tagsId) {
        int countSearchTags = tagsId.size();
        if (countSearchTags > 0) {
            InCondition inCondition = new InCondition(TAGS_ID_COLUMN);
            tagsId.forEach(inCondition::addObject);
            selectQuery.addCondition(inCondition);
            selectQuery.addHaving(BinaryCondition.equalTo(FunctionCall.count().addColumnParams(NEWS_ID_COLUMN), countSearchTags));
        }
    }

    private void addAuthorSort(SelectQuery selectQuery, boolean sort) {
        if (sort) {
            FunctionCall authorSurnameOrdering = FunctionCall.min().addColumnParams(AUTHORS_SURNAME_COLUMN);
            FunctionCall authorNameOrdering = FunctionCall.min().addColumnParams(AUTHORS_NAME_COLUMN);
            FunctionCall authorIdOrdering = FunctionCall.min().addColumnParams(AUTHORS_ID_COLUMN);
            selectQuery.addCustomOrderings(authorSurnameOrdering, authorNameOrdering , authorIdOrdering);
        }
    }

    private void addNewsCreationDateSort(SelectQuery selectQuery, boolean sort) {
        if (sort) {
            selectQuery.addOrderings(NEWS_CREATION_DATE_COLUMN);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindNewsBySearchCriteriaSpecification that = (FindNewsBySearchCriteriaSpecification) o;
        return Objects.equals(searchCriteria, that.searchCriteria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchCriteria);
    }
}

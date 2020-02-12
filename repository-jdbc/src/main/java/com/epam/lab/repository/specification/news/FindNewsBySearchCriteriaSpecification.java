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
        selectQuery.addAllTableColumns(newsTable)
                .addJoins(SelectQuery.JoinType.LEFT_OUTER, newsAuthorsNewsJoin, newsAuthorsAuthorsJoin, newsTagsNewsJoin, newsTagsTagsJoin);

        addAuthorConditionIfExist(selectQuery, searchCriteria.getAuthorId());
        addTagsConditionsIfExist(selectQuery, searchCriteria.getTagsId());
        addAuthorSort(selectQuery, searchCriteria.isAuthorSort());
        addNewsCreationDateSort(selectQuery, searchCriteria.isCreateDateSort());

        selectQuery.addGroupings(newsIdColumn);
        return selectQuery.validate().toString();
    }

    private void addAuthorConditionIfExist(SelectQuery selectQuery, Long authorId) {
        if (authorId != null) {
            selectQuery.addCondition(BinaryCondition.equalTo(authorsIdColumn, authorId));
        }
    }

    private void addTagsConditionsIfExist(SelectQuery selectQuery, Collection<Long> tagsId) {
        if (tagsId != null) {
            int countSearchTags = tagsId.size();
            if (countSearchTags > 0) {
                InCondition inCondition = new InCondition(tagsIdColumn);
                tagsId.forEach(inCondition::addObject);
                selectQuery.addCondition(inCondition);
                selectQuery.addHaving(BinaryCondition.equalTo(FunctionCall.count().addColumnParams(newsIdColumn), countSearchTags));
            }
        }
    }

    private void addAuthorSort(SelectQuery selectQuery, boolean sort) {
        if (sort) {
            FunctionCall authorSurnameOrdering = FunctionCall.min().addColumnParams(authorsSurnameColumn);
            FunctionCall authorNameOrdering = FunctionCall.min().addColumnParams(authorsNameColumn);
            FunctionCall authorIdOrdering = FunctionCall.min().addColumnParams(authorsIdColumn);
            selectQuery.addCustomOrderings(authorSurnameOrdering, authorNameOrdering , authorIdOrdering);
        }
    }

    private void addNewsCreationDateSort(SelectQuery selectQuery, boolean sort) {
        if (sort) {
            selectQuery.addOrderings(newsCreationDateColumn);
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

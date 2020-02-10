package com.epam.lab.repository.specification.news;

import com.epam.lab.repository.specification.Specification;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.ComboCondition;
import com.healthmarketscience.sqlbuilder.FunctionCall;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import java.util.Objects;

import static com.epam.lab.repository.DbInfo.*;

public class FindNewsBySearchCriteriaSpecification implements Specification {

    private SearchCriteria searchCriteria;

    public FindNewsBySearchCriteriaSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public String query() {
        int countSearchTags = searchCriteria.getTagsId().size();

        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllTableColumns(newsTable).addJoins(
                        SelectQuery.JoinType.LEFT_OUTER,
                        newsAuthorsNewsJoin,
                        newsAuthorsAuthorsJoin,
                        newsTagsNewsJoin,
                        newsTagsTagsJoin);

        if (searchCriteria.getAuthorId() != null) {
            selectQuery.addCondition(BinaryCondition.equalTo(authorsIdColumn, searchCriteria.getAuthorId()));
        }
        if (countSearchTags > 0) {
            ComboCondition comboOrCondition = ComboCondition.or();
            searchCriteria.getTagsId().forEach(tagId -> comboOrCondition.addCondition(BinaryCondition.equalTo(tagsIdColumn, tagId)));
            selectQuery.addCondition(comboOrCondition);
            selectQuery.addHaving(BinaryCondition.equalTo(FunctionCall.count().addColumnParams(newsIdColumn), countSearchTags));
        }
        if (searchCriteria.isAuthorSort()) {
            selectQuery.addCustomOrderings(FunctionCall.min().addColumnParams(authorsSurnameColumn), FunctionCall.min().addColumnParams(authorsNameColumn), FunctionCall.min().addColumnParams(authorsIdColumn));
        }
        if (searchCriteria.isCreateDateSort()) {
            selectQuery.addOrderings(newsCreationDateColumn);
        }
        selectQuery.addGroupings(newsIdColumn);

        return selectQuery.validate().toString();
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

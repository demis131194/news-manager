package com.epam.lab.repository.specification;

import com.healthmarketscience.sqlbuilder.*;

import java.util.ArrayList;
import java.util.List;

import static com.epam.lab.repository.DbInfo.*;

public class SearchCriteriaSpecification implements Specification {

    private SearchCriteria searchCriteria;

    public SearchCriteriaSpecification(SearchCriteria searchCriteria) {
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
            selectQuery.addCustomOrderings(FunctionCall.min().addColumnParams(authorsSurnameColumn), FunctionCall.min().addColumnParams(authorsNameColumn));
        }
        if (searchCriteria.isCreateDateSort()) {
            selectQuery.addOrderings(newsCreationDateColumn);
        }
        selectQuery.addGroupings(newsIdColumn);

        return selectQuery.validate().toString();
    }
}

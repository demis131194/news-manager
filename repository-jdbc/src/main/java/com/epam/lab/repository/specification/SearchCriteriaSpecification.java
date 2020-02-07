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
        BinaryCondition authorCondition = null;
        List<Condition> tagsConditions = new ArrayList<>();

        if (searchCriteria.getAuthorId() != null) {
            authorCondition = BinaryCondition.equalTo(authorsIdColumn, searchCriteria.getAuthorId());
        }

        searchCriteria.getTagsId().forEach(tagId -> tagsConditions.add(BinaryCondition.equalTo(tagsIdColumn, tagId)));

        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllTableColumns(newsTable)
                .addJoins(SelectQuery.JoinType.LEFT_OUTER, newsAuthorsNewsJoin, newsAuthorsAuthorsJoin, newsTagsNewsJoin, newsTagsTagsJoin);

        if (authorCondition != null) {
            selectQuery.addCondition(authorCondition);
        }
        if (tagsConditions.size() > 0) {
            ComboCondition comboOrCondition = ComboCondition.or();
            tagsConditions.forEach(comboOrCondition::addCondition);
            selectQuery.addCondition(comboOrCondition);
        }
        if (searchCriteria.isAuthorSort()) {
            selectQuery.addCustomOrderings(FunctionCall.min().addColumnParams(authorsSurnameColumn), FunctionCall.min().addColumnParams(authorsNameColumn));
        }
        if (searchCriteria.isCreateDateSort()) {
            selectQuery.addOrderings(newsCreationDateColumn);
        }
        selectQuery.addGroupings(newsIdColumn);
        if (tagsConditions.size() > 0) {
            selectQuery.addHaving(BinaryCondition.equalTo(FunctionCall.count().addColumnParams(newsIdColumn), tagsConditions.size()));
        }

        return selectQuery.validate().toString();
    }
}

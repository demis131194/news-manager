package com.epam.lab.repository.specification.tag;

import com.epam.lab.repository.specification.Specification;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import static com.epam.lab.repository.DbInfo.*;

public class FindTagByNameSpecification implements Specification {
    private String tagName;

    public FindTagByNameSpecification(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String query() {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllTableColumns(tagsTable);
        selectQuery.addCondition(BinaryCondition.equalTo(tagsNameColumn, tagName));
        return selectQuery.validate().toString();
    }
}

package com.epam.lab.repository.specification.author;

import com.epam.lab.repository.specification.Specification;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import static com.epam.lab.repository.DbInfo.*;

public class FindAuthorsByNameSpecification implements Specification {
    private String authorName;

    public FindAuthorsByNameSpecification(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String query() {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllTableColumns(AUTHORS_TABLE);
        selectQuery.addCondition(BinaryCondition.equalTo(AUTHORS_NAME_COLUMN, authorName));
        return selectQuery.validate().toString();
    }
}

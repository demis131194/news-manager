package com.epam.lab.repository.jdbc.specification.tag;

import com.epam.lab.repository.jdbc.specification.Specification;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import static com.epam.lab.repository.DbInfo.TAGS_NAME_COLUMN;
import static com.epam.lab.repository.DbInfo.TAGS_TABLE;

public class FindTagByNameSpecification implements Specification {
    private String tagName;

    public FindTagByNameSpecification(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public String query() {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllTableColumns(TAGS_TABLE);
        selectQuery.addCondition(BinaryCondition.equalTo(TAGS_NAME_COLUMN, tagName));
        return selectQuery.validate().toString();
    }
}

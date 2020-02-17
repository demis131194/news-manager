package com.epam.lab.repository.jdbc.specification.tag;

import com.epam.lab.repository.jdbc.specification.Specification;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import static com.epam.lab.repository.DbConstants.TAGS_TABLE;

public class FindAllTagsSpecification implements Specification {
    private static final SelectQuery SELECT_QUERY;

    static {
        SELECT_QUERY = new SelectQuery();
        SELECT_QUERY.addAllTableColumns(TAGS_TABLE);
    }

    @Override
    public String query() {
        return SELECT_QUERY.validate().toString();
    }
}

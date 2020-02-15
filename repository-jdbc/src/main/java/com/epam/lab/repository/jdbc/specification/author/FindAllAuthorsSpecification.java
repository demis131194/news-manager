package com.epam.lab.repository.jdbc.specification.author;

import com.epam.lab.repository.jdbc.specification.Specification;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import static com.epam.lab.repository.DbInfo.AUTHORS_TABLE;

public class FindAllAuthorsSpecification implements Specification {
    private static final SelectQuery SELECT_QUERY;

    static {
        SELECT_QUERY = new SelectQuery();
        SELECT_QUERY.addAllTableColumns(AUTHORS_TABLE);
    }

    @Override
    public String query() {
        return SELECT_QUERY.validate().toString();
    }
}

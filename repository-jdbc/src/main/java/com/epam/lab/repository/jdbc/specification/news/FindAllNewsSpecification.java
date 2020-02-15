package com.epam.lab.repository.jdbc.specification.news;

import com.epam.lab.repository.jdbc.specification.Specification;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import static com.epam.lab.repository.DbConstants.NEWS_TABLE;

public class FindAllNewsSpecification implements Specification {
    private static final SelectQuery SELECT_QUERY;

    static {
        SELECT_QUERY = new SelectQuery();
        SELECT_QUERY.addAllTableColumns(NEWS_TABLE);
    }

    @Override
    public String query() {
        return SELECT_QUERY.validate().toString();
    }
}

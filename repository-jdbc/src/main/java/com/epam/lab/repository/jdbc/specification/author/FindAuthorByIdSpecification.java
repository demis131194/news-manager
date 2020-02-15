package com.epam.lab.repository.jdbc.specification.author;

import com.epam.lab.repository.jdbc.specification.Specification;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import static com.epam.lab.repository.DbConstants.AUTHORS_ID_COLUMN;
import static com.epam.lab.repository.DbConstants.AUTHORS_TABLE;

public class FindAuthorByIdSpecification implements Specification {
    private static final SelectQuery SELECT_QUERY;

    private long authorId;

    static {
        SELECT_QUERY = new SelectQuery();
        SELECT_QUERY.addAllTableColumns(AUTHORS_TABLE);
        SELECT_QUERY.addCondition(BinaryCondition.equalTo(AUTHORS_ID_COLUMN, Condition.QUESTION_MARK));
    }

    public FindAuthorByIdSpecification(long authorId) {
        this.authorId = authorId;
    }

    public long getAuthorId() {
        return authorId;
    }

    @Override
    public String query() {
        return SELECT_QUERY.validate().toString().replace(Condition.QUESTION_MARK.toString(), String.valueOf(authorId));
    }
}

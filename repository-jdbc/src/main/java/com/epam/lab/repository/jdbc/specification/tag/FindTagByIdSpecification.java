package com.epam.lab.repository.jdbc.specification.tag;

import com.epam.lab.repository.jdbc.specification.Specification;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import static com.epam.lab.repository.DbConstants.TAGS_ID_COLUMN;
import static com.epam.lab.repository.DbConstants.TAGS_TABLE;

public class FindTagByIdSpecification implements Specification {
    private static final SelectQuery SELECT_QUERY;

    private long tagId;

    static {
        SELECT_QUERY = new SelectQuery();
        SELECT_QUERY.addAllTableColumns(TAGS_TABLE);
        SELECT_QUERY.addCondition(BinaryCondition.equalTo(TAGS_ID_COLUMN, Condition.QUESTION_MARK));
    }

    public FindTagByIdSpecification(long tagId) {
        this.tagId = tagId;
    }

    public long getTagId() {
        return tagId;
    }

    @Override
    public String query() {
        return SELECT_QUERY.validate().toString().replace(Condition.QUESTION_MARK.toString(), String.valueOf(tagId));
    }
}

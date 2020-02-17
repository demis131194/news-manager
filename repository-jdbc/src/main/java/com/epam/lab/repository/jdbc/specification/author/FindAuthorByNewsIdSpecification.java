package com.epam.lab.repository.jdbc.specification.author;

import com.epam.lab.repository.jdbc.specification.Specification;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import static com.epam.lab.repository.DbConstants.*;

public class FindAuthorByNewsIdSpecification implements Specification {
    private long newsId;

    public FindAuthorByNewsIdSpecification(long newsId) {
        this.newsId = newsId;
    }

    public long getNewsId() {
        return newsId;
    }

    @Override
    public String query() {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllTableColumns(AUTHORS_TABLE);
        selectQuery.addJoins(SelectQuery.JoinType.LEFT_OUTER, NEWS_AUTHORS_AUTHORS_JOIN);
        selectQuery.addCondition(BinaryCondition.equalTo(NEWS_AUTHORS_NEWS_ID_COLUMN, newsId));
        return selectQuery.validate().toString();
    }
}

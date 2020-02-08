package com.epam.lab.repository.specification.author;

import com.epam.lab.repository.specification.Specification;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import static com.epam.lab.repository.DbInfo.*;

public class FindAuthorByNewsIdSpecification implements Specification {
    private long newsId;

    public FindAuthorByNewsIdSpecification(long newsId) {
        this.newsId = newsId;
    }

    @Override
    public String query() {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllTableColumns(authorsTable);
        selectQuery.addJoins(SelectQuery.JoinType.LEFT_OUTER, newsAuthorsAuthorsJoin);
        selectQuery.addCondition(BinaryCondition.equalTo(newsAuthorsNewsIdColumn, newsId));
        return selectQuery.validate().toString();
    }
}

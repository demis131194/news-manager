package com.epam.lab.repository.jdbc.specification.tag;

import com.epam.lab.repository.jdbc.specification.Specification;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import static com.epam.lab.repository.DbInfo.*;

public class FindTagsByNewsIdSpecification implements Specification {
    private long newsId;

    public FindTagsByNewsIdSpecification(long newsId) {
        this.newsId = newsId;
    }

    public long getNewsId() {
        return newsId;
    }

    @Override
    public String query() {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllTableColumns(TAGS_TABLE);
        selectQuery.addJoins(SelectQuery.JoinType.LEFT_OUTER, NEWS_TAGS_TAGS_JOIN);
        selectQuery.addCondition(BinaryCondition.equalTo(NEWS_TAGS_NEWS_ID_COLUMN, newsId));
        return selectQuery.validate().toString();
    }
}

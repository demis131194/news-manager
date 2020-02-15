package com.epam.lab.repository.jdbc.specification.news;

import com.epam.lab.repository.jdbc.specification.Specification;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import static com.epam.lab.repository.DbInfo.NEWS_ID_COLUMN;
import static com.epam.lab.repository.DbInfo.NEWS_TABLE;

public class FindNewsByIdSpecification implements Specification {
    private static final SelectQuery SELECT_QUERY;

    private long newsId;

    static {
        SELECT_QUERY = new SelectQuery();
        SELECT_QUERY.addAllTableColumns(NEWS_TABLE);
        SELECT_QUERY.addCondition(BinaryCondition.equalTo(NEWS_ID_COLUMN, Condition.QUESTION_MARK));
    }

    public FindNewsByIdSpecification(long newsId) {
        this.newsId = newsId;
    }

    public long getNewsId() {
        return newsId;
    }

    @Override
    public String query() {
        return SELECT_QUERY.validate().toString().replace(Condition.QUESTION_MARK.toString(), String.valueOf(newsId));
    }
}

package com.epam.lab.repository.jdbc.specification.tag;

import com.epam.lab.repository.jdbc.specification.Specification;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import java.util.Objects;

import static com.epam.lab.repository.DbInfo.*;

public class FindTagsByNewsIdSpecification implements Specification {
    private long newsId;

    public FindTagsByNewsIdSpecification(long newsId) {
        this.newsId = newsId;
    }

    @Override
    public String query() {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllTableColumns(TAGS_TABLE);
        selectQuery.addJoins(SelectQuery.JoinType.LEFT_OUTER, NEWS_TAGS_TAGS_JOIN);
        selectQuery.addCondition(BinaryCondition.equalTo(NEWS_TAGS_NEWS_ID_COLUMN, newsId));
        return selectQuery.validate().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindTagsByNewsIdSpecification that = (FindTagsByNewsIdSpecification) o;
        return newsId == that.newsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId);
    }
}

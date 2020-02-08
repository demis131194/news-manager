package com.epam.lab.repository.specification.tag;

import com.epam.lab.repository.specification.Specification;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import static com.epam.lab.repository.DbInfo.*;

public class FindTagsByNewsIdSpecification implements Specification {

    private long newsId;

    public FindTagsByNewsIdSpecification(long newsId) {
        this.newsId = newsId;
    }

    @Override
    public String query() {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllTableColumns(tagsTable);
        selectQuery.addJoins(SelectQuery.JoinType.LEFT_OUTER, newsTagsTagsJoin);
        selectQuery.addCondition(BinaryCondition.equalTo(newsTagsNewsIdColumn, newsId));
        return selectQuery.validate().toString();
    }
}

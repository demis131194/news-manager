package com.epam.lab.repository.specification.tag;

import com.epam.lab.repository.specification.Specification;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import java.util.Objects;

import static com.epam.lab.repository.DbInfo.tagsNameColumn;
import static com.epam.lab.repository.DbInfo.tagsTable;

public class FindTagByNameSpecification implements Specification {
    private String tagName;

    public FindTagByNameSpecification(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String query() {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllTableColumns(tagsTable);
        selectQuery.addCondition(BinaryCondition.equalTo(tagsNameColumn, tagName));
        return selectQuery.validate().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindTagByNameSpecification that = (FindTagByNameSpecification) o;
        return Objects.equals(tagName, that.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName);
    }
}

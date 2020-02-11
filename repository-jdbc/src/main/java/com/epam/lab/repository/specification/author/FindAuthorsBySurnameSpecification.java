package com.epam.lab.repository.specification.author;

import com.epam.lab.repository.specification.Specification;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.SelectQuery;

import static com.epam.lab.repository.DbInfo.*;

public class FindAuthorsBySurnameSpecification implements Specification {
    private String authorSurname;

    public FindAuthorsBySurnameSpecification(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    @Override
    public String query() {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllTableColumns(authorsTable);
        selectQuery.addCondition(BinaryCondition.equalTo(authorsSurnameColumn, authorSurname));
        return selectQuery.validate().toString();
    }
}
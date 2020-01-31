package com.epam.lab.repository.mapper;

import com.epam.lab.model.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String SURNAME_COLUMN = "surname";

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Author(rs.getLong(ID_COLUMN),
                rs.getString(NAME_COLUMN),
                rs.getString(SURNAME_COLUMN)
        );
    }
}

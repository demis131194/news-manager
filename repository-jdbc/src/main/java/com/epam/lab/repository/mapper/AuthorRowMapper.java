package com.epam.lab.repository.mapper;

import com.epam.lab.model.Author;
import com.epam.lab.repository.DbInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorRowMapper implements RowMapper<Author> {
    public static final String ID_COLUMN = DbInfo.AUTHORS_ID_COLUMN_NAME;
    public static final String NAME_COLUMN = DbInfo.AUTHORS_NAME_COLUMN_NAME;
    public static final String SURNAME_COLUMN = DbInfo.AUTHORS_SURNAME_COLUMN_NAME;

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Author(rs.getLong(ID_COLUMN),
                rs.getString(NAME_COLUMN),
                rs.getString(SURNAME_COLUMN)
        );
    }
}

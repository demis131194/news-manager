package com.epam.lab.repository.mapper;

import com.epam.lab.model.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TagRowMapper implements RowMapper<Tag> {
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";

    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Tag(rs.getLong(ID_COLUMN),
                rs.getString(NAME_COLUMN)
        );
    }
}

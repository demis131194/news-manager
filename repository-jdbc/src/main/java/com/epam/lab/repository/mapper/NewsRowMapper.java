package com.epam.lab.repository.mapper;

import com.epam.lab.model.News;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsRowMapper implements RowMapper<News> {
    public static final String ID_COLUMN = "id";
    public static final String TITLE_COLUMN = "title";
    public static final String SHORT_TEXT_COLUMN = "short_text";
    public static final String FULL_TEXT_COLUMN = "full_text";
    public static final String CREATION_DATE_COLUMN = "creation_date";
    public static final String MODIFICATION_DATE_COLUMN = "modification_date";

    @Override
    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new News(rs.getLong(ID_COLUMN),
                rs.getString(TITLE_COLUMN),
                rs.getString(SHORT_TEXT_COLUMN),
                rs.getString(FULL_TEXT_COLUMN),
                rs.getTimestamp(CREATION_DATE_COLUMN).toLocalDateTime(),
                rs.getTimestamp(MODIFICATION_DATE_COLUMN).toLocalDateTime()
        );
    }
}

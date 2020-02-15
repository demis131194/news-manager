package com.epam.lab.repository.jdbc.mapper;

import com.epam.lab.model.News;
import com.epam.lab.repository.DbConstants;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class NewsRowMapper implements RowMapper<News> {
    public static final String ID_COLUMN = DbConstants.NEWS_ID_COLUMN_NAME;
    public static final String TITLE_COLUMN = DbConstants.NEWS_TITLE_COLUMN_NAME;
    public static final String SHORT_TEXT_COLUMN = DbConstants.NEWS_SHORT_TEXT_COLUMN_NAME;
    public static final String FULL_TEXT_COLUMN = DbConstants.NEWS_FULL_TEXT_COLUMN_NAME;
    public static final String CREATION_DATE_COLUMN = DbConstants.NEWS_CREATION_DATE_COLUMN_NAME;
    public static final String MODIFICATION_DATE_COLUMN = DbConstants.NEWS_MODIFICATION_DATE_COLUMN_NAME;

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

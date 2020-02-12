package com.epam.lab.repository;

import com.healthmarketscience.sqlbuilder.dbspec.basic.*;

/**
 * The type Db info.
 */
public class DbInfo {
    public static final String NEWS_TABLE_NAME = "news";
    public static final String AUTHORS_TABLE_NAME = "authors";
    public static final String TAGS_TABLE_NAME = "tags";
    public static final String NEWS_TAGS_TABLE_NAME = "news_tags";
    public static final String NEWS_AUTHORS_TABLE_NAME = "news_authors";
    public static final String USERS_TABLE_NAME = "users";
    public static final String ROLES_TABLE_NAME = "roles";

    public static final String NEWS_ID_COLUMN_NAME = "id";
    public static final String NEWS_TITLE_COLUMN_NAME = "title";
    public static final String NEWS_SHORT_TEXT_COLUMN_NAME = "short_text";
    public static final String NEWS_FULL_TEXT_COLUMN_NAME = "full_text";
    public static final String NEWS_CREATION_DATE_COLUMN_NAME = "creation_date";
    public static final String NEWS_MODIFICATION_DATE_COLUMN_NAME = "modification_date";
    public static final String AUTHORS_ID_COLUMN_NAME = "id";
    public static final String AUTHORS_NAME_COLUMN_NAME = "name";
    public static final String AUTHORS_SURNAME_COLUMN_NAME = "surname";
    public static final String TAGS_ID_COLUMN_NAME = "id";
    public static final String TAGS_NAME_COLUMN_NAME = "name";
    public static final String NEWS_TAGS_NEWS_ID_COLUMN_NAME = "news_id";
    public static final String NEWS_TAGS_TAG_ID_COLUMN_NAME = "tag_id";
    public static final String NEWS_AUTHORS_NEWS_ID_COLUMN_NAME = "news_id";
    public static final String NEWS_AUTHORS_AUTHOR_ID_COLUMN_NAME = "author_id";
    public static final String USERS_ID_COLUMN_NAME = "id";
    public static final String USERS_NAME_COLUMN_NAME = "name";
    public static final String USERS_SURNAME_COLUMN_NAME = "surname";
    public static final String USERS_LOGIN_COLUMN_NAME = "login";
    public static final String USERS_PASSWORD_COLUMN_NAME = "password";
    public static final String ROLES_USER_ID_COLUMN_NAME = "user_id";
    public static final String ROLES_ROLE_NAME_COLUMN_NAME = "role_name";


    public static final DbSpec DB_SPEC = new DbSpec();
    public static final DbSchema DB_SCHEMA = DB_SPEC.addDefaultSchema();

    public static final DbTable NEWS_TABLE = DB_SCHEMA.addTable(NEWS_TABLE_NAME);
    public static final DbColumn NEWS_ID_COLUMN = NEWS_TABLE.addColumn(NEWS_ID_COLUMN_NAME);
    public static final DbColumn NEWS_TITLE_COLUMN = NEWS_TABLE.addColumn(NEWS_TITLE_COLUMN_NAME);
    public static final DbColumn NEWS_SHORT_TEXT_COLUMN = NEWS_TABLE.addColumn(NEWS_SHORT_TEXT_COLUMN_NAME);
    public static final DbColumn NEWS_FULL_TEXT_COLUMN = NEWS_TABLE.addColumn(NEWS_FULL_TEXT_COLUMN_NAME);
    public static final DbColumn NEWS_CREATION_DATE_COLUMN = NEWS_TABLE.addColumn(NEWS_CREATION_DATE_COLUMN_NAME);
    public static final DbColumn NEWS_MODIFICATION_DATE_COLUMN = NEWS_TABLE.addColumn(NEWS_MODIFICATION_DATE_COLUMN_NAME);

    public static final DbTable AUTHORS_TABLE = DB_SCHEMA.addTable(AUTHORS_TABLE_NAME);
    public static final DbColumn AUTHORS_ID_COLUMN = AUTHORS_TABLE.addColumn(AUTHORS_ID_COLUMN_NAME);
    public static final DbColumn AUTHORS_NAME_COLUMN = AUTHORS_TABLE.addColumn(AUTHORS_NAME_COLUMN_NAME);
    public static final DbColumn AUTHORS_SURNAME_COLUMN = AUTHORS_TABLE.addColumn(AUTHORS_SURNAME_COLUMN_NAME);

    public static final DbTable TAGS_TABLE = DB_SCHEMA.addTable(TAGS_TABLE_NAME);
    public static final DbColumn TAGS_ID_COLUMN = TAGS_TABLE.addColumn(TAGS_ID_COLUMN_NAME);
    public static final DbColumn TAGS_NAME_COLUMN = TAGS_TABLE.addColumn(TAGS_NAME_COLUMN_NAME);

    public static final DbTable NEWS_TAGS_TABLE = DB_SCHEMA.addTable(NEWS_TAGS_TABLE_NAME);
    public static final DbColumn NEWS_TAGS_NEWS_ID_COLUMN = NEWS_TAGS_TABLE.addColumn(NEWS_TAGS_NEWS_ID_COLUMN_NAME);
    public static final DbColumn NEWS_TAGS_TAGS_ID_COLUMN = NEWS_TAGS_TABLE.addColumn(NEWS_TAGS_TAG_ID_COLUMN_NAME);

    public static final DbTable NEWS_AUTHORS_TABLE = DB_SCHEMA.addTable(NEWS_AUTHORS_TABLE_NAME);
    public static final DbColumn NEWS_AUTHORS_NEWS_ID_COLUMN = NEWS_AUTHORS_TABLE.addColumn(NEWS_AUTHORS_NEWS_ID_COLUMN_NAME);
    public static final DbColumn NEWS_AUTHORS_AUTHORS_ID_COLUMN = NEWS_AUTHORS_TABLE.addColumn(NEWS_AUTHORS_AUTHOR_ID_COLUMN_NAME);

    public static final DbTable USERS_TABLE = DB_SCHEMA.addTable(USERS_TABLE_NAME);
    public static final DbColumn USERS_ID_COLUMN = USERS_TABLE.addColumn(USERS_ID_COLUMN_NAME);
    public static final DbColumn USERS_NAME_COLUMN = USERS_TABLE.addColumn(USERS_NAME_COLUMN_NAME);
    public static final DbColumn USERS_SURNAME_COLUMN = USERS_TABLE.addColumn(USERS_SURNAME_COLUMN_NAME);
    public static final DbColumn USERS_LOGIN_COLUMN = USERS_TABLE.addColumn(USERS_LOGIN_COLUMN_NAME);
    public static final DbColumn USERS_PASSWORD_COLUMN = USERS_TABLE.addColumn(USERS_PASSWORD_COLUMN_NAME);

    public static final DbTable ROLES_TABLE = DB_SCHEMA.addTable(ROLES_TABLE_NAME);
    public static final DbColumn ROLES_USER_ID_COLUMN = USERS_TABLE.addColumn(ROLES_USER_ID_COLUMN_NAME);
    public static final DbColumn ROLES_ROLE_NAME_COLUMN = USERS_TABLE.addColumn(ROLES_ROLE_NAME_COLUMN_NAME);

    public static final DbJoin NEWS_AUTHORS_NEWS_JOIN = DB_SPEC.addJoin(null, NEWS_AUTHORS_TABLE_NAME,
            null, NEWS_TABLE_NAME,
            new String[]{NEWS_AUTHORS_NEWS_ID_COLUMN_NAME}, new String[]{NEWS_ID_COLUMN_NAME}
    );
    public static final DbJoin NEWS_AUTHORS_AUTHORS_JOIN = DB_SPEC.addJoin(null, NEWS_AUTHORS_TABLE_NAME,
            null, AUTHORS_TABLE_NAME,
            new String[]{NEWS_AUTHORS_AUTHOR_ID_COLUMN_NAME}, new String[]{AUTHORS_ID_COLUMN_NAME}
    );
    public static final DbJoin NEWS_TAGS_NEWS_JOIN = DB_SPEC.addJoin(null, NEWS_TABLE_NAME,
            null, NEWS_TAGS_TABLE_NAME,
            new String[]{NEWS_ID_COLUMN_NAME}, new String[]{NEWS_TAGS_NEWS_ID_COLUMN_NAME}
    );
    public static final DbJoin NEWS_TAGS_TAGS_JOIN = DB_SPEC.addJoin(null, NEWS_TAGS_TABLE_NAME,
            null, TAGS_TABLE_NAME,
            new String[]{NEWS_TAGS_TAG_ID_COLUMN_NAME}, new String[]{TAGS_ID_COLUMN_NAME}
    );
}

package com.epam.lab.repository;

/**
 * The type Db info.
 */
public class DbConstants {
    public static final String NEWS_TABLE_NAME = "news";
    public static final String AUTHORS_TABLE_NAME = "authors";
    public static final String TAGS_TABLE_NAME = "tags";
    public static final String NEWS_TAGS_TABLE_NAME = "news_tags";
    public static final String NEWS_AUTHORS_TABLE_NAME = "news_authors";
    public static final String USERS_TABLE_NAME = "users";
    public static final String ROLES_TABLE_NAME = "roles";

    public static final String ID_COLUMN_NAME = "id";

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
}

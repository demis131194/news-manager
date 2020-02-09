package com.epam.lab.repository;

import com.healthmarketscience.sqlbuilder.dbspec.basic.*;

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


    public static final DbSpec dbSpec = new DbSpec();
    public static final DbSchema dbSchema = dbSpec.addDefaultSchema();

    public static final DbTable newsTable = dbSchema.addTable(NEWS_TABLE_NAME);
    public static final DbColumn newsIdColumn = newsTable.addColumn(NEWS_ID_COLUMN_NAME);
    public static final DbColumn newsTitleColumn = newsTable.addColumn(NEWS_TITLE_COLUMN_NAME);
    public static final DbColumn newsShortTextColumn = newsTable.addColumn(NEWS_SHORT_TEXT_COLUMN_NAME);
    public static final DbColumn newsFullTextColumn = newsTable.addColumn(NEWS_FULL_TEXT_COLUMN_NAME);
    public static final DbColumn newsCreationDateColumn = newsTable.addColumn(NEWS_CREATION_DATE_COLUMN_NAME);
    public static final DbColumn newsModificationDateColumn = newsTable.addColumn(NEWS_MODIFICATION_DATE_COLUMN_NAME);

    public static final DbTable authorsTable = dbSchema.addTable(AUTHORS_TABLE_NAME);
    public static final DbColumn authorsIdColumn = authorsTable.addColumn(AUTHORS_ID_COLUMN_NAME);
    public static final DbColumn authorsNameColumn = authorsTable.addColumn(AUTHORS_NAME_COLUMN_NAME);
    public static final DbColumn authorsSurnameColumn = authorsTable.addColumn(AUTHORS_SURNAME_COLUMN_NAME);

    public static final DbTable tagsTable = dbSchema.addTable(TAGS_TABLE_NAME);
    public static final DbColumn tagsIdColumn = tagsTable.addColumn(TAGS_ID_COLUMN_NAME);
    public static final DbColumn tagsNameColumn = tagsTable.addColumn(TAGS_NAME_COLUMN_NAME);

    public static final DbTable newsTagsTable = dbSchema.addTable(NEWS_TAGS_TABLE_NAME);
    public static final DbColumn newsTagsNewsIdColumn = newsTagsTable.addColumn(NEWS_TAGS_NEWS_ID_COLUMN_NAME);
    public static final DbColumn newsTagsTagsIdColumn = newsTagsTable.addColumn(NEWS_TAGS_TAG_ID_COLUMN_NAME);

    public static final DbTable newsAuthorsTable = dbSchema.addTable(NEWS_AUTHORS_TABLE_NAME);
    public static final DbColumn newsAuthorsNewsIdColumn = newsAuthorsTable.addColumn(NEWS_AUTHORS_NEWS_ID_COLUMN_NAME);
    public static final DbColumn newsAuthorsAuthorsIdColumn = newsAuthorsTable.addColumn(NEWS_AUTHORS_AUTHOR_ID_COLUMN_NAME);

    public static final DbTable usersTable = dbSchema.addTable(USERS_TABLE_NAME);
    public static final DbColumn usersIdColumn = usersTable.addColumn(USERS_ID_COLUMN_NAME);
    public static final DbColumn usersNameColumn = usersTable.addColumn(USERS_NAME_COLUMN_NAME);
    public static final DbColumn usersSurnameColumn = usersTable.addColumn(USERS_SURNAME_COLUMN_NAME);
    public static final DbColumn usersLoginColumn = usersTable.addColumn(USERS_LOGIN_COLUMN_NAME);
    public static final DbColumn usersPasswordColumn = usersTable.addColumn(USERS_PASSWORD_COLUMN_NAME);

    public static final DbTable rolesTable = dbSchema.addTable(ROLES_TABLE_NAME);
    public static final DbColumn rolesUserIdColumn = usersTable.addColumn(ROLES_USER_ID_COLUMN_NAME);
    public static final DbColumn rolesRoleNameColumn = usersTable.addColumn(ROLES_ROLE_NAME_COLUMN_NAME);

    public static final DbJoin newsAuthorsNewsJoin = dbSpec.addJoin(null, NEWS_AUTHORS_TABLE_NAME, null, NEWS_TABLE_NAME, new String[]{NEWS_AUTHORS_NEWS_ID_COLUMN_NAME}, new String[]{NEWS_ID_COLUMN_NAME});
    public static final DbJoin newsAuthorsAuthorsJoin = dbSpec.addJoin(null, NEWS_AUTHORS_TABLE_NAME, null, AUTHORS_TABLE_NAME, new String[]{NEWS_AUTHORS_AUTHOR_ID_COLUMN_NAME}, new String[]{AUTHORS_ID_COLUMN_NAME});
    public static final DbJoin newsTagsNewsJoin = dbSpec.addJoin(null, NEWS_TABLE_NAME, null, NEWS_TAGS_TABLE_NAME, new String[]{NEWS_ID_COLUMN_NAME}, new String[]{NEWS_TAGS_NEWS_ID_COLUMN_NAME});
    public static final DbJoin newsTagsTagsJoin = dbSpec.addJoin(null, NEWS_TAGS_TABLE_NAME, null, TAGS_TABLE_NAME, new String[]{NEWS_TAGS_TAG_ID_COLUMN_NAME}, new String[]{TAGS_ID_COLUMN_NAME});
}

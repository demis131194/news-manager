package com.epam.lab.repository.search;

import com.epam.lab.model.Author;
import com.epam.lab.model.Tag;
import com.healthmarketscience.sqlbuilder.*;
import com.healthmarketscience.sqlbuilder.dbspec.basic.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SearchCriteria {
    private Long authorId;
    private List<Long> tagsId = new ArrayList<>();
    private boolean isAuthorSort;
    private boolean isCreateDateSort;

    public SearchCriteria(Long authorId, List<Long> tagsId, boolean isAuthorSort, boolean isCreateDateSort) {
        this.authorId = authorId;
        if (tagsId != null) {
            this.tagsId.addAll(tagsId);
        }
        this.isAuthorSort = isAuthorSort;
        this.isCreateDateSort = isCreateDateSort;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public List<Long> getTagsId() {
        return Collections.unmodifiableList(tagsId);
    }

    public boolean isAuthorSort() {
        return isAuthorSort;
    }

    public boolean isCreateDateSort() {
        return isCreateDateSort;
    }

//    DbSpec dbSpec = new DbSpec();
//    DbSchema dbSchema = dbSpec.addDefaultSchema();
//
//    DbTable newsTable = dbSchema.addTable(NEWS_TABLE_NAME);
//    DbColumn newsIdColumn = newsTable.addColumn(NEWS_ID_COLUMN_NAME);
//    DbColumn newsTitleColumn = newsTable.addColumn(NEWS_TITLE_COLUMN_NAME);
//    DbColumn newsShortTextColumn = newsTable.addColumn(NEWS_SHORT_TEXT_COLUMN_NAME);
//    DbColumn newsFullTextColumn = newsTable.addColumn(NEWS_FULL_TEXT_COLUMN_NAME);
//    DbColumn newsCreationDateColumn = newsTable.addColumn(NEWS_CREATION_DATE_COLUMN_NAME);
//    DbColumn newsModificationDateColumn = newsTable.addColumn(NEWS_MODIFICATION_DATE_COLUMN_NAME);
//
//    DbTable authorsTable = dbSchema.addTable(AUTHORS_TABLE_NAME);
//    DbColumn authorsIdColumn = authorsTable.addColumn(AUTHORS_ID_COLUMN_NAME);
//    DbColumn authorsNameColumn = authorsTable.addColumn(AUTHORS_NAME_COLUMN_NAME);
//    DbColumn authorsSurnameColumn = authorsTable.addColumn(AUTHORS_SURNAME_COLUMN_NAME);
//
//    DbTable tagsTable = dbSchema.addTable(TAGS_TABLE_NAME);
//    DbColumn tagsIdColumn = tagsTable.addColumn(TAGS_ID_COLUMN_NAME);
//    DbColumn tagsNameColumn = tagsTable.addColumn(TAGS_NAME_COLUMN_NAME);
//
//    DbTable newsTagsTable = dbSchema.addTable(NEWS_TAGS_TABLE_NAME);
//    DbColumn newsTagsNewsIdColumn = newsTagsTable.addColumn(NEWS_TAGS_NEWS_ID_COLUMN_NAME);
//    DbColumn newsTagsTagsIdColumn = newsTagsTable.addColumn(NEWS_TAGS_TAGS_ID_COLUMN_NAME);
//
//    DbTable newsAuthorsTable = dbSchema.addTable(NEWS_AUTHORS_TABLE_NAME);
//    DbColumn newsAuthorsNewsIdColumn = newsAuthorsTable.addColumn(NEWS_AUTHORS_NEWS_ID_COLUMN_NAME);
//    DbColumn newsAuthorsAuthorsIdColumn = newsAuthorsTable.addColumn(NEWS_AUTHORS_AUTHORS_ID_COLUMN_NAME);
//
//    DbJoin newsAuthorsNewsJoin = dbSpec.addJoin(null, NEWS_AUTHORS_TABLE_NAME, null, NEWS_TABLE_NAME, new String[]{NEWS_AUTHORS_NEWS_ID_COLUMN_NAME}, new String[]{NEWS_ID_COLUMN_NAME});
//    DbJoin newsAuthorsAuthorsJoin = dbSpec.addJoin(null, NEWS_AUTHORS_TABLE_NAME, null, AUTHORS_TABLE_NAME, new String[]{NEWS_AUTHORS_AUTHORS_ID_COLUMN_NAME}, new String[]{AUTHORS_ID_COLUMN_NAME});
//    DbJoin newsTagsNewsJoin = dbSpec.addJoin(null, NEWS_TABLE_NAME, null, NEWS_TAGS_TABLE_NAME, new String[]{NEWS_ID_COLUMN_NAME}, new String[]{NEWS_TAGS_NEWS_ID_COLUMN_NAME});
//    DbJoin newsTagsTagsJoin = dbSpec.addJoin(null, NEWS_TAGS_TABLE_NAME, null, TAGS_TABLE_NAME, new String[]{NEWS_TAGS_TAGS_ID_COLUMN_NAME}, new String[]{TAGS_ID_COLUMN_NAME});
//
//    SearchCriteria searchCriteria_1 = new SearchCriteria(1L, null, false, false);
//    SearchCriteria searchCriteria_1_1 = new SearchCriteria(1L, Arrays.asList(1L, 3L), false, false);
//    SearchCriteria searchCriteria_2 = new SearchCriteria(5L, Arrays.asList(1L, 3L), false, false);
//    SearchCriteria searchCriteria_3 = new SearchCriteria(7L, Arrays.asList(1L, 3L), false, false);
//    SearchCriteria searchCriteria_4 = new SearchCriteria(null, null, false, false);
//    SearchCriteria searchCriteria = searchCriteria_1_1;
//
//    BinaryCondition authorCondition = null;
//    List<Condition> tagsConditions = new ArrayList<>();
//
//        if (searchCriteria.getAuthorId() != null) {
//        authorCondition = BinaryCondition.equalTo(authorsIdColumn, searchCriteria.getAuthorId());
//    }
//        if (searchCriteria.getTagsId() != null && searchCriteria.getTagsId().size() > 0) {
//        searchCriteria.getTagsId().forEach(tagId -> tagsConditions.add(BinaryCondition.equalTo(tagsIdColumn, tagId)));
//    }
//
//    SelectQuery selectQuery = new SelectQuery();
//        selectQuery
//                .addColumns(newsIdColumn, newsTitleColumn, newsShortTextColumn, newsFullTextColumn, newsCreationDateColumn, newsModificationDateColumn)
//            .addJoins(SelectQuery.JoinType.LEFT_OUTER, newsAuthorsNewsJoin, newsAuthorsAuthorsJoin, newsTagsNewsJoin, newsTagsTagsJoin);
//
//        if (authorCondition != null) {
//        selectQuery.addCondition(authorCondition);
//    }
//        if (tagsConditions.size() > 0) {
//        ComboCondition or = ComboCondition.or();
//        tagsConditions.forEach(or::addCondition);
//        selectQuery.addCondition(or);
//    }
//        if (searchCriteria.isAuthorSort()) {
//        selectQuery.addOrderings(authorsSurnameColumn, authorsNameColumn);
//    }
//        if (searchCriteria.isCreateDateSort()) {
//        selectQuery.addOrderings(newsCreationDateColumn);
//    }
//
//        selectQuery.addGroupings(newsIdColumn);
//
//        if (tagsConditions.size() > 0) {
//        selectQuery.addHaving(BinaryCondition.equalTo(FunctionCall.count().addColumnParams(newsIdColumn), tagsConditions.size()));
//    }
//
//    String s = selectQuery.validate().toString();
//
//
//        System.out.println(s);
}

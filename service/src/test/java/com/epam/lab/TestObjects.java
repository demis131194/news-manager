package com.epam.lab;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.dto.NewsTo;
import com.epam.lab.dto.TagTo;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.jdbc.specification.news.SearchCriteria;

import java.util.Arrays;
import java.util.Collections;

public class TestObjects {

    public static final long INIT_TEST_ID = 1L;

    public static final Tag EXPECTED_TAG_1 = new Tag(INIT_TEST_ID, "History");
    public static final Tag EXPECTED_TAG_2 = new Tag(INIT_TEST_ID + 1, "SCIENCE");
    public static final Tag EXPECTED_TAG_3 = new Tag(INIT_TEST_ID + 2, "FANNY");
    public static final Tag CREATE_TEST_TAG_4 = new Tag( null,"CREATE TEST");
    public static final Tag UPDATE_TEST_TAG_5 = new Tag(INIT_TEST_ID + 4, "UPDATE TEST");
    public static final int EXPECTED_COUNT_ALL_TAGS = 3;

    public static final TagTo EXPECTED_DTO_TAG_1 = new TagTo(EXPECTED_TAG_1.getId(), EXPECTED_TAG_1.getName());
    public static final TagTo EXPECTED_DTO_TAG_2 = new TagTo(EXPECTED_TAG_2.getId(), EXPECTED_TAG_2.getName());
    public static final TagTo EXPECTED_DTO_TAG_3 = new TagTo(EXPECTED_TAG_3.getId(), EXPECTED_TAG_3.getName());
    public static final TagTo CREATE_DTO_TAG_4 = new TagTo(INIT_TEST_ID + 3, CREATE_TEST_TAG_4.getName());
    public static final TagTo UPDATE_DTO_TAG_5 = new TagTo(UPDATE_TEST_TAG_5.getId(), UPDATE_TEST_TAG_5.getName());

    public static final Author EXPECTED_AUTHOR_1 = new Author(INIT_TEST_ID, "DIMA", "FORD");
    public static final Author EXPECTED_AUTHOR_2 = new Author(INIT_TEST_ID + 1, "VASYA", "VASYA");
    public static final Author EXPECTED_AUTHOR_3 = new Author(INIT_TEST_ID + 2, "SOVA", "SOVA");
    public static final Author CREATE_TEST_AUTHOR_4 = new Author(null, "Create name", "Create surname");
    public static final Author UPDATE_TEST_AUTHOR_5 = new Author(INIT_TEST_ID + 4, "Update TEST", "Update Test");
    public static final int EXPECTED_COUNT_ALL_AUTHORS = 3;

    public static final AuthorTo EXPECTED_DTO_AUTHOR_1 = new AuthorTo(EXPECTED_AUTHOR_1.getId(), EXPECTED_AUTHOR_1.getName(), EXPECTED_AUTHOR_1.getSurname());
    public static final AuthorTo EXPECTED_DTO_AUTHOR_2 = new AuthorTo(EXPECTED_AUTHOR_2.getId(), EXPECTED_AUTHOR_2.getName(), EXPECTED_AUTHOR_2.getSurname());
    public static final AuthorTo EXPECTED_DTO_AUTHOR_3 = new AuthorTo(EXPECTED_AUTHOR_3.getId(), EXPECTED_AUTHOR_3.getName(), EXPECTED_AUTHOR_3.getSurname());
    public static final AuthorTo CREATE_TEST_DTO_AUTHOR_4 = new AuthorTo(INIT_TEST_ID + 3, CREATE_TEST_AUTHOR_4.getName(), CREATE_TEST_AUTHOR_4.getSurname());
    public static final AuthorTo UPDATE_TEST_DTO_AUTHOR_5 = new AuthorTo(UPDATE_TEST_AUTHOR_5.getId(), UPDATE_TEST_AUTHOR_5.getName(), UPDATE_TEST_AUTHOR_5.getSurname());

    public static final News EXPECTED_NEWS_1 = new News(INIT_TEST_ID, "News title 1", "Short text 1", "Full text 1");
    public static final News EXPECTED_NEWS_2 = new News(INIT_TEST_ID + 1, "News title 2", "Short text 2", "Full text 2");
    public static final News EXPECTED_NEWS_3 = new News(INIT_TEST_ID + 2, "News title 3", "Short text 3", "Full text 3");
    public static final News CREATE_TEST_NEWS_4 = new News(null, "Create", "create", "create test");
    public static final News UPDATE_TEST_NEWS_5 = new News(INIT_TEST_ID + 4, "Update title", "Update TEST", "Update Test");
    public static final int EXPECTED_COUNT_ALL_NEWS = 3;

    public static final NewsTo EXPECTED_DTO_NEWS_1 = new NewsTo(EXPECTED_NEWS_1.getId(), EXPECTED_NEWS_1.getTitle(), EXPECTED_NEWS_1.getShortText(), EXPECTED_NEWS_1.getFullText(),
            EXPECTED_DTO_AUTHOR_1,
            Arrays.asList(EXPECTED_DTO_TAG_1, EXPECTED_DTO_TAG_2)
    );
    public static final NewsTo EXPECTED_DTO_NEWS_2 = new NewsTo(EXPECTED_NEWS_2.getId(), EXPECTED_NEWS_2.getTitle(), EXPECTED_NEWS_2.getShortText(), EXPECTED_NEWS_2.getFullText(),
            EXPECTED_DTO_AUTHOR_2,
            Arrays.asList(EXPECTED_DTO_TAG_1, EXPECTED_DTO_TAG_3)
    );
    public static final NewsTo EXPECTED_DTO_NEWS_3 = new NewsTo(EXPECTED_NEWS_3.getId(), EXPECTED_NEWS_3.getTitle(), EXPECTED_NEWS_3.getShortText(), EXPECTED_NEWS_3.getFullText(),
            EXPECTED_DTO_AUTHOR_3,
            Collections.singletonList(EXPECTED_DTO_TAG_3)
    );
    public static final NewsTo CREATE_TEST_DTO_NEWS_4 = new NewsTo(INIT_TEST_ID + 3, CREATE_TEST_NEWS_4.getTitle(), CREATE_TEST_NEWS_4.getShortText(), CREATE_TEST_NEWS_4.getFullText(),
            EXPECTED_DTO_AUTHOR_3,
            Collections.singletonList(EXPECTED_DTO_TAG_3)
    );
    public static final NewsTo UPDATE_TEST_DTO_NEWS_5 = new NewsTo(UPDATE_TEST_NEWS_5.getId(), UPDATE_TEST_NEWS_5.getTitle(), UPDATE_TEST_NEWS_5.getShortText(), UPDATE_TEST_NEWS_5.getFullText(),
            EXPECTED_DTO_AUTHOR_3,
            Arrays.asList(EXPECTED_DTO_TAG_2, EXPECTED_DTO_TAG_3)
    );

    public static final SearchCriteria SEARCH_CRITERIA_1 = new SearchCriteria(EXPECTED_AUTHOR_1.getId(), Arrays.asList(EXPECTED_TAG_1.getId(), EXPECTED_TAG_2.getId()), false, false);

}

package com.epam.lab.repository;

import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;

public class DbTestObjects {

    public static final long INIT_TEST_ID = 1L;

    public static final Tag EXPECTED_TAG_1 = new Tag(INIT_TEST_ID, "History");
    public static final Tag EXPECTED_TAG_2 = new Tag(INIT_TEST_ID + 1, "SCIENCE");
    public static final Tag EXPECTED_TAG_3 = new Tag(INIT_TEST_ID + 2, "FANNY");
    public static final int EXPECTED_COUNT_ALL_TAGS = 3;

    public static final Author EXPECTED_AUTHOR_1 = new Author(INIT_TEST_ID, "DIMA", "FORD");
    public static final Author EXPECTED_AUTHOR_2 = new Author(INIT_TEST_ID + 1, "VASYA", "VASYA");
    public static final Author EXPECTED_AUTHOR_3 = new Author(INIT_TEST_ID + 2, "SOVA", "SOVA");
    public static final int EXPECTED_COUNT_ALL_AUTHORS = 3;

    public static final News EXPECTED_NEWS_1 = new News(INIT_TEST_ID, "News title 1", "Short text 1", "Full text 1");
    public static final News EXPECTED_NEWS_2 = new News(INIT_TEST_ID + 1, "News title 2", "Short text 2", "Full text 2");
    public static final News EXPECTED_NEWS_3 = new News(INIT_TEST_ID + 2, "News title 3", "Short text 3", "Full text 3");
    public static final int EXPECTED_COUNT_ALL_NEWS = 3;
}

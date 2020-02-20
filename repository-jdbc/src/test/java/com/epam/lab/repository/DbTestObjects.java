package com.epam.lab.repository;

import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;

public class DbTestObjects {

    public static final long INIT_TEST_ID = 1L;

    public static final Tag EXPECTED_TAG_1 = new Tag(INIT_TEST_ID, "History");
    public static final Tag EXPECTED_TAG_2 = new Tag(INIT_TEST_ID + 1, "Science");
    public static final Tag EXPECTED_TAG_3 = new Tag(INIT_TEST_ID + 2, "Comedy");
    public static final Tag EXPECTED_TAG_4 = new Tag(INIT_TEST_ID + 3, "Nature");
    public static final Tag EXPECTED_TAG_5 = new Tag(INIT_TEST_ID + 4, "Art");
    public static final Tag EXPECTED_TAG_6 = new Tag(INIT_TEST_ID + 5, "My");
    public static final Tag EXPECTED_TAG_7 = new Tag(INIT_TEST_ID + 6, "Dogs");
    public static final Tag EXPECTED_TAG_8 = new Tag(INIT_TEST_ID + 7, "Cats");
    public static final int EXPECTED_COUNT_ALL_TAGS = 8;

    public static final Author EXPECTED_AUTHOR_1 = new Author(INIT_TEST_ID, "Dima", "Ford");
    public static final Author EXPECTED_AUTHOR_2 = new Author(INIT_TEST_ID + 1, "Vasya", "Pupkin");
    public static final Author EXPECTED_AUTHOR_3 = new Author(INIT_TEST_ID + 2, "Sova", "Sovna");
    public static final Author EXPECTED_AUTHOR_4 = new Author(INIT_TEST_ID + 3, "Artem", "Ford");
    public static final Author EXPECTED_AUTHOR_5 = new Author(INIT_TEST_ID + 4, "Vasya", "Asin");
    public static final Author EXPECTED_AUTHOR_6 = new Author(INIT_TEST_ID + 5, "Nikita", "Semenov");
    public static final Author EXPECTED_AUTHOR_7 = new Author(INIT_TEST_ID + 6, "Dima", "Ford");
    public static final Author EXPECTED_AUTHOR_8 = new Author(INIT_TEST_ID + 7, "Dima", "Jackson");
    public static final int EXPECTED_COUNT_ALL_AUTHORS = 8;

    public static final News EXPECTED_NEWS_1 = new News(INIT_TEST_ID, "Robokop", "Short text 1", "Full text 1");
    public static final News EXPECTED_NEWS_2 = new News(INIT_TEST_ID + 1, "WoRk", "Short text 2", "Full text 2");
    public static final News EXPECTED_NEWS_3 = new News(INIT_TEST_ID + 2, "work", "Short text 3", "Full text 3");
    public static final News EXPECTED_NEWS_4 = new News(INIT_TEST_ID + 3, "News", "Short text 4", "Full text 4");
    public static final News EXPECTED_NEWS_5 = new News(INIT_TEST_ID + 4, "Boring", "Short text 5", "Full text 5");
    public static final News EXPECTED_NEWS_6 = new News(INIT_TEST_ID + 5, "Bomb shel", "Short text 6", "Full text 6");
    public static final News EXPECTED_NEWS_7 = new News(INIT_TEST_ID + 6, "UFO", "Short text 7", "Full text 7");
    public static final News EXPECTED_NEWS_8 = new News(INIT_TEST_ID + 7, "Warning", "Short text 8", "Full text 8");
    public static final News EXPECTED_NEWS_9 = new News(INIT_TEST_ID + 8, "JAVA core", "Short text 9", "Full text 9");
    public static final News EXPECTED_NEWS_10 = new News(INIT_TEST_ID + 9, "Spring", "Short text 10", "Full text 10");
    public static final News EXPECTED_NEWS_11 = new News(INIT_TEST_ID + 10, "Postgresql", "Short text 11", "Full text 11");
    public static final int EXPECTED_COUNT_ALL_NEWS = 11;
}

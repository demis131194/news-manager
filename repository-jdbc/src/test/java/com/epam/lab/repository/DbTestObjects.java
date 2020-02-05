package com.epam.lab.repository;

import com.epam.lab.model.Author;
import com.epam.lab.model.Tag;

public class DbTestObjects {

    public static final long INIT_TEST_ID = 1L;

    public static final long INIT_SEQUENCE_ALL_ID = 10000L;

    public static final Tag EXPECTED_TAG_1 = new Tag(INIT_TEST_ID, "History");
    public static final Tag EXPECTED_TAG_2 = new Tag(INIT_TEST_ID + 1, "SCIENCE");
    public static final Tag EXPECTED_TAG_3 = new Tag(INIT_TEST_ID + 2, "FANNY");

    public static final Author EXPECTED_AUTHOR_1 = new Author(INIT_TEST_ID, "DIMA", "FORD");
    public static final Author EXPECTED_AUTHOR_2 = new Author(INIT_TEST_ID + 1, "VASYA", "VASYA");
    public static final Author EXPECTED_AUTHOR_3 = new Author(INIT_TEST_ID + 2, "SOVA", "SOVA");



}

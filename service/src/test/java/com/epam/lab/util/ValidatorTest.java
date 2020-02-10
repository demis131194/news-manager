package com.epam.lab.util;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.dto.NewsTo;
import com.epam.lab.dto.TagTo;
import com.epam.lab.validator.Validator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    private static TagTo validTagTo;
    private static TagTo invalidTagToNullName;
    private static TagTo invalidTagToInvalidId;
    private static TagTo invalidTagToTooLongName;

    private static AuthorTo validAuthorTo;
    private static AuthorTo invalidAuthorToNullName;
    private static AuthorTo invalidAuthorToNullSurname;
    private static AuthorTo invalidAuthorToLongName;
    private static AuthorTo invalidAuthorToLongSurname;
    private static AuthorTo invalidAuthorToInvalidId;

    private static NewsTo validNewsTo;
    private static NewsTo invalidNewsToInvalidAuthorTo;
    private static NewsTo invalidNewsToNullAuthorTo;
    private static NewsTo invalidNewsToInvalidTagsTo;
    private static NewsTo invalidNewsToNullTagsTo;
    private static NewsTo invalidNewsToLongTitle;
    private static NewsTo invalidNewsToNullTitle;
    private static NewsTo invalidNewsToLongShortText;
    private static NewsTo invalidNewsToNullShortText;
    private static NewsTo invalidNewsToLongFullText;
    private static NewsTo invalidNewsToNullFullText;
    private static NewsTo invalidNewsToInvalidId;

    private static final long VALID_ID = 1L;
    private static final long INVALID_ID = -4L;

    private static final String VALID_TAG_NAME = "History";
    private static final String INVALID_LONG_TAG_NAME;

    private static final String VALID_AUTHOR_NAME = "Dima";
    private static final String INVALID_LONG_AUTHOR_NAME;
    private static final String VALID_AUTHOR_SURNAME = "Drozd";
    private static final String INVALID_LONG_AUTHOR_SURNAME;

    private static final String VALID_NEWS_TITLE = "Title";
    private static final String INVALID_NEWS_LONG_TITLE;
    private static final String VALID_NEWS_SHORT_TEXT = "Short Text";
    private static final String INVALID_NEWS_LONG_SHORT_TEXT;
    private static final String VALID_NEWS_FULL_TEXT = "Full text";
    private static final String INVALID_NEWS_LONG_FULL_TEXT;

    static {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i < 31; i++) {
            builder.append("D");
        }
        INVALID_LONG_TAG_NAME = builder.toString();
        INVALID_LONG_AUTHOR_NAME = builder.toString();
        INVALID_LONG_AUTHOR_SURNAME = builder.toString();
        INVALID_NEWS_LONG_TITLE = builder.toString();
        for (int i=0; i < 70; i++) {
            builder.append("D");
        }
        INVALID_NEWS_LONG_SHORT_TEXT = builder.toString();
        for (int i=0; i < 2000; i++) {
            builder.append("D");
        }
        INVALID_NEWS_LONG_FULL_TEXT = builder.toString();
    }

    @BeforeClass
    public static void initDto() {
        validTagTo = new TagTo(VALID_ID, VALID_TAG_NAME);
        invalidTagToNullName = new TagTo(VALID_ID, null);
        invalidTagToInvalidId = new TagTo(INVALID_ID, VALID_TAG_NAME);
        invalidTagToTooLongName = new TagTo(VALID_ID, INVALID_LONG_TAG_NAME);


        validAuthorTo = new AuthorTo(VALID_ID, VALID_AUTHOR_NAME, VALID_AUTHOR_SURNAME);
        invalidAuthorToNullName = new AuthorTo(VALID_ID, null, VALID_AUTHOR_SURNAME);
        invalidAuthorToNullSurname = new AuthorTo(VALID_ID, VALID_AUTHOR_NAME, null);

        invalidAuthorToLongName = new AuthorTo(VALID_ID, INVALID_LONG_AUTHOR_NAME, VALID_AUTHOR_SURNAME);
        invalidAuthorToLongSurname = new AuthorTo(VALID_ID, VALID_AUTHOR_NAME, INVALID_LONG_AUTHOR_SURNAME);
        invalidAuthorToInvalidId = new AuthorTo(INVALID_ID, VALID_AUTHOR_NAME, VALID_AUTHOR_SURNAME);


        TagTo validTagToForNews = new TagTo(2L, "Science");
        validNewsTo = new NewsTo(VALID_ID, VALID_NEWS_TITLE, VALID_NEWS_SHORT_TEXT, VALID_NEWS_FULL_TEXT,
                validAuthorTo,
                Arrays.asList(validTagTo, validTagToForNews)
        );
        invalidNewsToInvalidAuthorTo = new NewsTo(VALID_ID, VALID_NEWS_TITLE, VALID_NEWS_SHORT_TEXT, VALID_NEWS_FULL_TEXT,
                invalidAuthorToLongName,
                Arrays.asList(validTagTo, validTagToForNews)
        );
        invalidNewsToNullAuthorTo = new NewsTo(VALID_ID, VALID_NEWS_TITLE, VALID_NEWS_SHORT_TEXT, VALID_NEWS_FULL_TEXT,
                null,
                Arrays.asList(validTagTo, validTagToForNews)
        );
        invalidNewsToInvalidTagsTo = new NewsTo(VALID_ID, VALID_NEWS_TITLE, VALID_NEWS_SHORT_TEXT, VALID_NEWS_FULL_TEXT,
                validAuthorTo,
                Arrays.asList(validTagTo, invalidTagToTooLongName)
        );
        invalidNewsToNullTagsTo = new NewsTo(VALID_ID, VALID_NEWS_TITLE, VALID_NEWS_SHORT_TEXT, VALID_NEWS_FULL_TEXT,
                validAuthorTo,
                null
        );
        invalidNewsToLongTitle = new NewsTo(VALID_ID, INVALID_NEWS_LONG_TITLE, VALID_NEWS_SHORT_TEXT, VALID_NEWS_FULL_TEXT,
                validAuthorTo,
                Arrays.asList(validTagTo, validTagToForNews)
        );
        invalidNewsToNullTitle = invalidNewsToLongTitle = new NewsTo(VALID_ID, null, VALID_NEWS_SHORT_TEXT, VALID_NEWS_FULL_TEXT,
                validAuthorTo,
                Arrays.asList(validTagTo, validTagToForNews)
        );
        invalidNewsToLongShortText = new NewsTo(VALID_ID, VALID_NEWS_TITLE, INVALID_NEWS_LONG_SHORT_TEXT, VALID_NEWS_FULL_TEXT,
                validAuthorTo,
                Arrays.asList(validTagTo, validTagToForNews)
        );
        invalidNewsToNullShortText = new NewsTo(VALID_ID, VALID_NEWS_TITLE, null, VALID_NEWS_FULL_TEXT,
                validAuthorTo,
                Arrays.asList(validTagTo, validTagToForNews)
        );
        invalidNewsToLongFullText = new NewsTo(VALID_ID, VALID_NEWS_TITLE, VALID_NEWS_SHORT_TEXT, INVALID_NEWS_LONG_FULL_TEXT,
                validAuthorTo,
                Arrays.asList(validTagTo, validTagToForNews)
        );
        invalidNewsToNullFullText = new NewsTo(VALID_ID, VALID_NEWS_TITLE, VALID_NEWS_SHORT_TEXT, null,
                validAuthorTo,
                Arrays.asList(validTagTo, validTagToForNews)
        );
        invalidNewsToInvalidId = new NewsTo(INVALID_ID, VALID_NEWS_TITLE, VALID_NEWS_SHORT_TEXT, VALID_NEWS_FULL_TEXT,
                validAuthorTo,
                Arrays.asList(validTagTo, validTagToForNews)
        );

    }

    @Test
    public void validTagToTest() {
        boolean isValid = Validator.validate(validTagTo);
        assertTrue(isValid);
    }

    @Test
    public void invalidTagToNullNameTest() {
        boolean isValid = Validator.validate(invalidTagToNullName);
        assertFalse(isValid);
    }

    @Test
    public void invalidTagToInvalidIdTest() {
        boolean isValid = Validator.validate(invalidTagToInvalidId);
        assertFalse(isValid);
    }

    @Test
    public void invalidTagToTooLongNameTest() {
        boolean isValid = Validator.validate(invalidTagToTooLongName);
        assertFalse(isValid);
    }

    @Test
    public void validAuthorToTest() {
        boolean isValid = Validator.validate(validAuthorTo);
        assertTrue(isValid);
    }

    @Test
    public void invalidAuthorToNullNameTest() {
        boolean isValid = Validator.validate(invalidAuthorToNullName);
        assertFalse(isValid);
    }

    @Test
    public void invalidAuthorToNullSurnameTest() {
        boolean isValid = Validator.validate(invalidAuthorToNullSurname);
        assertFalse(isValid);
    }

    @Test
    public void invalidAuthorToLongNameTest() {
        boolean isValid = Validator.validate(invalidAuthorToLongName);
        assertFalse(isValid);
    }

    @Test
    public void invalidAuthorToLongSurnameTest() {
        boolean isValid = Validator.validate(invalidAuthorToLongSurname);
        assertFalse(isValid);
    }

    @Test
    public void invalidAuthorToInvalidIdTest() {
        boolean isValid = Validator.validate(invalidAuthorToInvalidId);
        assertFalse(isValid);
    }

    @Test
    public void validNewsToTest() {
        boolean isValid = Validator.validate(validNewsTo);
        assertTrue(isValid);
    }

    @Test
    public void invalidNewsToInvalidAuthorToTest() {
        boolean isValid = Validator.validate(invalidNewsToInvalidAuthorTo);
        assertFalse(isValid);
    }

    @Test
    public void invalidNewsToNullAuthorToTest() {
        boolean isValid = Validator.validate(invalidNewsToNullAuthorTo);
        assertFalse(isValid);
    }

    @Test
    public void invalidNewsToInvalidTagsToTest() {
        boolean isValid = Validator.validate(invalidNewsToInvalidTagsTo);
        assertFalse(isValid);
    }

    @Test
    public void invalidNewsToNullTagsToTest() {
        boolean isValid = Validator.validate(invalidNewsToNullTagsTo);
        assertFalse(isValid);
    }

    @Test
    public void invalidNewsToLongTitleTest() {
        boolean isValid = Validator.validate(invalidNewsToLongTitle);
        assertFalse(isValid);
    }

    @Test
    public void invalidNewsToNullTitleTest() {
        boolean isValid = Validator.validate(invalidNewsToNullTitle);
        assertFalse(isValid);
    }

    @Test
    public void invalidNewsToLongShortTextTest() {
        boolean isValid = Validator.validate(invalidNewsToLongShortText);
        assertFalse(isValid);
    }

    @Test
    public void invalidNewsToNullShortTextTest() {
        boolean isValid = Validator.validate(invalidNewsToNullShortText);
        assertFalse(isValid);
    }

    @Test
    public void invalidNewsToLongFullTextTest() {
        boolean isValid = Validator.validate(invalidNewsToLongFullText);
        assertFalse(isValid);
    }

    @Test
    public void invalidNewsToNullFullTextTest() {
        boolean isValid = Validator.validate(invalidNewsToNullFullText);
        assertFalse(isValid);
    }

    @Test
    public void invalidNewsToInvalidIdTest() {
        boolean isValid = Validator.validate(invalidNewsToInvalidId);
        assertFalse(isValid);
    }

    @Test
    public void validateIdTest() {
        boolean isValid = Validator.validateId(VALID_ID);
        assertTrue(isValid);
    }

    @Test
    public void validateIdFailTest() {
        boolean isValid = Validator.validateId(INVALID_ID);
        assertFalse(isValid);
    }

    @Test
    public void validateTagNameTest() {
        boolean isValid = Validator.validateTagName(VALID_TAG_NAME);
        assertTrue(isValid);
    }

    @Test
    public void validateTagNameFailTest() {
        boolean isValid = Validator.validateTagName(INVALID_LONG_TAG_NAME);
        assertFalse(isValid);
    }



}
package com.epam.lab.util;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.dto.NewsTo;
import com.epam.lab.dto.TagTo;

import java.util.Set;

public class Validator {

    private static final int DTO_MIN_ID = 0;
    private static final int TAG_MAX_NAME_LENGTH = 30;

    private static final int AUTHOR_MAX_NAME_LENGTH = 30;
    private static final int AUTHOR_MAX_SURNAME_LENGTH = 30;

    private static final int NEWS_MAX_TITLE_LENGTH = 30;
    private static final int NEWS_MAX_SHORT_TEXT_LENGTH = 100;
    private static final int NEWS_MAX_FULL_TEXT_LENGTH = 2000;

    public static boolean validate(NewsTo newsTo) {
        if (newsTo != null) {
            AuthorTo authorTo = newsTo.getAuthor();
            boolean isValidAuthor = validate(authorTo);

            Set<TagTo> tagsTo = newsTo.getTags();

            boolean isValidTags = tagsTo != null
                    && tagsTo.stream()
                    .map(Validator::validate)
                    .reduce(true, (bool_1, bool_2) -> bool_1 && bool_2);

            boolean isValidNews = (newsTo.getId() == null || validateId(newsTo.getId()))
                    && (newsTo.getTitle() != null && newsTo.getTitle().length() <= NEWS_MAX_TITLE_LENGTH)
                    && (newsTo.getShortText() != null && newsTo.getShortText().length() <= NEWS_MAX_SHORT_TEXT_LENGTH)
                    && (newsTo.getFullText() != null && newsTo.getFullText().length() <= NEWS_MAX_FULL_TEXT_LENGTH);

            return isValidAuthor && isValidTags && isValidNews;
        }
        return false;
    }

    public static boolean validate(AuthorTo author) {
        return author != null
                && (author.getId() == null || validateId(author.getId()))
                && (author.getName() != null && author.getName().length() <= AUTHOR_MAX_NAME_LENGTH)
                && (author.getSurname() != null && author.getSurname().length() <= AUTHOR_MAX_SURNAME_LENGTH);
    }

    public static boolean validate(TagTo tag) {
        return tag != null
                && (tag.getId() == null || validateId(tag.getId()))
                && validateTagName(tag.getName());
    }

    public static boolean validateId(Long id) {
        return id != null && id > DTO_MIN_ID;
    }

    public static boolean validateTagName(String tagName) {
        return tagName != null && tagName.length() <= TAG_MAX_NAME_LENGTH;
    }

}

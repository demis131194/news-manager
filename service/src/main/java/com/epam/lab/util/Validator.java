package com.epam.lab.util;

import com.epam.lab.dto.NewsTo;
import com.epam.lab.model.Author;
import com.epam.lab.model.Tag;

public class Validator {

    public static boolean validate(NewsTo newsTo) {
        return newsTo != null
                && newsTo.getAuthor() != null
                && newsTo.getTags() != null
                && newsTo.getTags().size() != 0
                && newsTo.getNews() != null;
    }

    public static boolean validate(Author author) {
        return author != null
                && author.getName() != null
                && author.getSurname() != null;
    }

    public static boolean validate(Tag tag) {
        return tag != null
                && tag.getName() != null;
    }

}

package com.epam.lab.util;

import com.epam.lab.dto.AuthorTo;
import com.epam.lab.dto.NewsTo;
import com.epam.lab.model.Tag;

public class Validator {

    public static boolean validate(NewsTo newsTo) {
        return newsTo != null
                && newsTo.getAuthor() != null
                && newsTo.getAuthor().getName() != null
                && newsTo.getAuthor().getSurname() != null
                && newsTo.getTags() != null
                && newsTo.getTags().size() != 0
                && newsTo.getTitle() != null
                && newsTo.getShortText() != null
                && newsTo.getFullText() != null;
    }

    public static boolean validate(AuthorTo author) {
        return author != null
                && author.getName() != null
                && author.getSurname() != null;
    }

    public static boolean validate(Tag tag) {
        return tag != null
                && tag.getName() != null;
    }

}

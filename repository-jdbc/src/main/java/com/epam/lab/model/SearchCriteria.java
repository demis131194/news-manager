package com.epam.lab.model;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteria {
    private Author author;
    private List<Tag> tags = new ArrayList<>();

    private SearchCriteria() {
    }

    public static class SearchCriteriaBuilder {
        private SearchCriteria newSearchCriteria;

        public SearchCriteriaBuilder() {
            this.newSearchCriteria = new SearchCriteria();
        }

        public SearchCriteriaBuilder setAuthor(Author author) {
            newSearchCriteria.author = author;
            return this;
        }

        public SearchCriteriaBuilder addTag(Tag tag) {
            newSearchCriteria.tags.add(tag);
            return this;
        }

        public SearchCriteriaBuilder addTags(List<Tag> tags) {
            newSearchCriteria.tags.addAll(tags);
            return this;
        }

        public SearchCriteria build() {
            return newSearchCriteria;
        }
    }
}

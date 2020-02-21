package com.epam.lab.repository.jpa.specification.news;

import java.util.*;

public class SearchCriteria {
    private Long authorId;
    private Set<Long> tagsId = new HashSet<>();
    private boolean isAuthorSort;
    private boolean isCreateDateSort;

    public SearchCriteria(Long authorId, Collection<Long> tagsId, boolean isAuthorSort, boolean isCreateDateSort) {
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

    public Set<Long> getTagsId() {
        return Collections.unmodifiableSet(tagsId);
    }

    public boolean isAuthorSort() {
        return isAuthorSort;
    }

    public boolean isCreateDateSort() {
        return isCreateDateSort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchCriteria that = (SearchCriteria) o;
        return isAuthorSort == that.isAuthorSort &&
                isCreateDateSort == that.isCreateDateSort &&
                Objects.equals(authorId, that.authorId) &&
                Objects.equals(tagsId, that.tagsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, tagsId, isAuthorSort, isCreateDateSort);
    }
}

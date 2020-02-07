package com.epam.lab.repository.specification;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchCriteria {
    private Long authorId;
    private Set<Long> tagsId = new HashSet<>();
    private boolean isAuthorSort;
    private boolean isCreateDateSort;

    public SearchCriteria(Long authorId, List<Long> tagsId, boolean isAuthorSort, boolean isCreateDateSort) {
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

}

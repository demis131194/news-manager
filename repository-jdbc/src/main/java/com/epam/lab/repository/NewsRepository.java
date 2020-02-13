package com.epam.lab.repository;

import com.epam.lab.model.News;

public interface NewsRepository extends SpecificationRepository<News> {
    boolean createNewsTagBound(long newsId, long authorId);
    boolean createNewsAuthorBound(long newsId, long authorId);
    boolean updateNewsAuthorBound(long newsId, long authorId);
    boolean deleteNewsTagBound(long newsId, long authorId);
    boolean deleteAllNewsTagBounds(long newsId);
    boolean deleteNewsAuthorBound(long newsId);
}

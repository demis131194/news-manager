package com.epam.lab.service;

import com.epam.lab.dto.NewsTo;
import com.epam.lab.model.News;
import com.epam.lab.repository.specification.JpaSpecification;
import com.epam.lab.repository.specification.news.SearchCriteria;

import java.util.List;

public interface NewsService extends BaseService<NewsTo> {
    List<NewsTo> findAllBySearchCriteria(SearchCriteria searchCriteria);
    List<NewsTo> findAllBySearchCriteria(SearchCriteria searchCriteria, int page, int count);
    long countAll(SearchCriteria searchCriteria);
}

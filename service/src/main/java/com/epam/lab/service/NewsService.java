package com.epam.lab.service;

import com.epam.lab.dto.NewsTo;
import com.epam.lab.repository.jdbc.specification.news.SearchCriteria;

import java.util.List;

public interface NewsService extends BaseService<NewsTo> {
    List<NewsTo> findAll(SearchCriteria searchCriteria);
}

package com.epam.lab.service;

import com.epam.lab.dto.NewsTo;
import com.epam.lab.repository.specification.news.SearchCriteria;

import java.util.List;

public interface NewsServiceInterface extends BaseService<NewsTo> {
    List<NewsTo> findAll(SearchCriteria searchCriteria);
}

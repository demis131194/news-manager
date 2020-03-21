package com.epam.lab.repository;

import com.epam.lab.model.News;
import com.epam.lab.repository.specification.JpaSpecification;

import java.util.List;

public interface NewsRepository extends BaseSpecificationRepository<News> {
    long countAll(JpaSpecification<News> specification);
    List<News> findAllBySpecification(JpaSpecification<News> specification, int page, int count);
}

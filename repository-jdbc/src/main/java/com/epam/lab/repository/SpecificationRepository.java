package com.epam.lab.repository;

import com.epam.lab.repository.specification.Specification;

import java.util.List;

public interface SpecificationRepository<T> {
    long create(T obj);
    boolean update(T obj);
    boolean delete(long id);
    T findById(long id);
    List<T> findAll();
    int countAll();
    List<T> findBySpecification(Specification specification);
}

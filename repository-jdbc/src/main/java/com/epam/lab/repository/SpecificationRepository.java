package com.epam.lab.repository;

import com.epam.lab.repository.specification.Specification;

import java.util.Collection;

public interface SpecificationRepository<T> {
    long create(T obj);
    boolean update(T obj);
    boolean delete(long id);
    T findById(long id);
    Collection<T> findAll();
    int countAll();
    Collection<T> findBySpecification(Specification specification);
}

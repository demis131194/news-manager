package com.epam.lab.repository;


import com.epam.lab.repository.jpa.specification.JpaSpecification;

import java.util.List;

/**
 * The interface Specification repository.
 *
 * @param <T> the type parameter
 */
public interface BaseSpecificationRepository<T> {
    T save(T obj);
    boolean delete(long id);
    T findBySpecification(JpaSpecification<T> specification);
    List<T> findAllBySpecification(JpaSpecification<T> specification);
    List<T> findAll();
    long countAll();
}

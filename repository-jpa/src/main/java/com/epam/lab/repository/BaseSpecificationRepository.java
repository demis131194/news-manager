package com.epam.lab.repository;


import com.epam.lab.repository.jpa.specification.JpaSpecification;

import java.util.List;

/**
 * The interface Specification repository.
 *
 * @param <T> the type parameter
 */
public interface BaseSpecificationRepository<T> {
    /**
     * Create long.
     *
     * @param obj the obj
     * @return the long
     */
    T save(T obj);

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean delete(long id);

    /**
     * Find by specification list.
     *
     * @param specification the specification
     * @return the list
     */
    List<T> findBySpecification(JpaSpecification<T> specification);

    /**
     * Count all int.
     *
     * @return the int
     */
    long countAll();
}

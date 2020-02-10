package com.epam.lab.repository;

import com.epam.lab.repository.specification.Specification;

import java.util.List;

/**
 * The interface Specification repository.
 *
 * @param <T> the type parameter
 */
public interface SpecificationRepository<T> {
    /**
     * Create long.
     *
     * @param obj the obj
     * @return the long
     */
    long create(T obj);

    /**
     * Update boolean.
     *
     * @param obj the obj
     * @return the boolean
     */
    boolean update(T obj);

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean delete(long id);

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    T findById(long id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<T> findAll();

    /**
     * Count all int.
     *
     * @return the int
     */
    int countAll();

    /**
     * Find by specification list.
     *
     * @param specification the specification
     * @return the list
     */
    List<T> findBySpecification(Specification specification);
}

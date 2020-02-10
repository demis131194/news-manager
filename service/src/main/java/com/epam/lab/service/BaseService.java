package com.epam.lab.service;

import java.util.List;

/**
 * The interface Base service.
 *
 * @param <T> the type parameter
 */
public interface BaseService<T> {
    /**
     * Create t.
     *
     * @param obj the obj
     * @return the t
     */
    T create(T obj);

    /**
     * Update t.
     *
     * @param obj the obj
     * @return the t
     */
    T update(T obj);

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
}

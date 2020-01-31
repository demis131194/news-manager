package com.epam.lab.repository;

import java.util.List;

public interface BaseCrudRepository<T> {
    long create(T obj);
    boolean update(T obj);
    boolean delete(long id);
    T findById(long id);
    List<T> findAll();
}

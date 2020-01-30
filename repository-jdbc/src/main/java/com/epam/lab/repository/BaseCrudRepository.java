package com.epam.lab.repository;

import java.util.List;

public interface BaseCrudRepository<T> {
    void create(T obj);
    void update(T obj);
    void delete(long id);
    T findById(long id);
    List<T> findAll();
}

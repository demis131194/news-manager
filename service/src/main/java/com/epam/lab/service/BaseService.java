package com.epam.lab.service;

import java.util.Collection;

public interface BaseService<T> {
    T create(T obj);
    T update(T obj);
    boolean delete(long id);
    T findById(long id);
    Collection<T> findAll();
    int countAll();
}

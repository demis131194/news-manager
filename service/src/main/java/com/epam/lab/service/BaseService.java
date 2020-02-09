package com.epam.lab.service;

import java.util.List;

public interface BaseService<T> {
    T create(T obj);
    T update(T obj);
    boolean delete(long id);
    T findById(long id);
    List<T> findAll();
    int countAll();
}

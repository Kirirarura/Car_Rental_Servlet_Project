package com.pavlenko.kyrylo.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T>{

    void create(T entity);
    Optional<T> findById(long id);
    List<T> findAll();
    void update(T entity);
    void delete(long id);
}

package com.pavlenko.kyrylo.model.dao;

import com.pavlenko.kyrylo.model.exeption.DataBaseException;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T>{

    void create(T entity) throws DataBaseException;
    T findById(Long id) throws DataBaseException;
    List<T> findAll() throws DataBaseException;
    void delete(long id) throws DataBaseException;
}

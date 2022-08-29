package com.pavlenko.kyrylo.model.dao;

import com.pavlenko.kyrylo.model.entity.User;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByUsernameAndPassword(String name, String password) throws DataBaseException;

    void blockById(int id) throws DataBaseException;

    void unblockById(int id) throws DataBaseException;

    boolean emailAlreadyExists(String email) throws DataBaseException;
}

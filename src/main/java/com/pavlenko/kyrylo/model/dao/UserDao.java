package com.pavlenko.kyrylo.model.dao;

import com.pavlenko.kyrylo.model.entity.User;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByUsernameAndPassword(String name, String password);

    List<User> findAllUsers();

    void blockById(int id) throws DataBaseException;

    void unblockById(int id) throws DataBaseException;

    boolean uniqueEmail(String email);
}

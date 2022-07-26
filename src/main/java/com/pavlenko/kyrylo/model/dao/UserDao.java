package com.pavlenko.kyrylo.model.dao;

import com.pavlenko.kyrylo.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByUsernameAndPassword(String name, String password);

    List<User> findAllUsers();

    void blockById(Long id);

    void unblockById(Long id);

    boolean uniqueEmail(String email);
}

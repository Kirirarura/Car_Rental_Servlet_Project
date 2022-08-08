package com.pavlenko.kyrylo.model.service;

import com.pavlenko.kyrylo.model.dao.UserDao;
import com.pavlenko.kyrylo.model.dto.UserDto;
import com.pavlenko.kyrylo.model.entity.Role;
import com.pavlenko.kyrylo.model.entity.User;
import com.pavlenko.kyrylo.model.exeption.AuthenticationException;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.exeption.EmailIsAlreadyRegisteredException;
import com.pavlenko.kyrylo.model.exeption.UserIsBlockedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
public class UserService {

    private final Logger LOG = LogManager.getLogger(UserService.class);
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public User authentication(String user, String password) throws UserIsBlockedException, AuthenticationException {
        Optional<User> optionalUser = findByUsernameAndPassword(user, password);

        if (optionalUser.isPresent()){
            if (!optionalUser.get().isBlocked()){
                return optionalUser.get();
            } else {
                LOG.warn("User ({}) is blocked", user);
                throw new UserIsBlockedException();
            }
        } else {
            LOG.warn("The user credentials entered are incorrect");
            throw new AuthenticationException();
        }
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    public void registerNewAccount(UserDto userDto) throws EmailIsAlreadyRegisteredException {
        checkUsernameIsUnique(userDto.getEmail());
        User user = new User(userDto, Role.RoleEnum.CUSTOMER);
        userDao.create(user);
        LOG.info("New account {} has been created", user);
    }

    public void registerNewManagerAccount(UserDto userDto) throws EmailIsAlreadyRegisteredException {
        checkUsernameIsUnique(userDto.getEmail());
        User user = new User(userDto, Role.RoleEnum.MANAGER);
        userDao.create(user);
        LOG.info("New account {} has been created", user);
    }

    private void checkUsernameIsUnique(String email) throws EmailIsAlreadyRegisteredException {
        if (userDao.uniqueEmail(email)) {
            LOG.info("An account with such email {} is already reserved", email);
            throw new EmailIsAlreadyRegisteredException();
        }
    }

    public void blockById(int id) throws DataBaseException {
        userDao.blockById(id);
        LOG.info("User (id = {}) has been blocked", id);
    }

    public void unblockById(int id) throws DataBaseException {
        userDao.unblockById(id);
        LOG.info("User (id = {}) has been unblocked", id);
    }

}

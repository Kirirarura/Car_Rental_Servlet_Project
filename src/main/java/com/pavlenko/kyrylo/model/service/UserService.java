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

    private final Logger logger = LogManager.getLogger(UserService.class);
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findById(Long id) throws DataBaseException {
        return userDao.findById(id);
    }

    public List<User> findAllUsers() throws DataBaseException {
        return userDao.findAll();
    }

    public User authentication(String user, String password) throws UserIsBlockedException, AuthenticationException, DataBaseException {
        Optional<User> optionalUser = userDao.findByUsernameAndPassword(user, password);

        if (optionalUser.isPresent()){
            if (!optionalUser.get().isBlocked()){
                return optionalUser.get();
            } else {
                logger.warn("User ({}) is blocked", user);
                throw new UserIsBlockedException();
            }
        } else {
            logger.warn("The user credentials entered are incorrect");
            throw new AuthenticationException();
        }
    }

    public void registerNewAccount(UserDto userDto) throws EmailIsAlreadyRegisteredException, DataBaseException {
        checkEmailIsUnique(userDto.getEmail());
        User user = new User(userDto, Role.RoleEnum.CUSTOMER);
        userDao.create(user);
        logger.info("New user account {} has been created", user);
    }

    public void registerNewManagerAccount(UserDto userDto) throws EmailIsAlreadyRegisteredException, DataBaseException {
        checkEmailIsUnique(userDto.getEmail());
        User user = new User(userDto, Role.RoleEnum.MANAGER);
        userDao.create(user);
        logger.info("New manager account {} has been created", user);
    }

    private void checkEmailIsUnique(String email) throws EmailIsAlreadyRegisteredException, DataBaseException {
        if (userDao.emailAlreadyExists(email)) {
            logger.info("An account with such email {} is already reserved", email);
            throw new EmailIsAlreadyRegisteredException();
        }
    }

    public void blockById(int id) throws DataBaseException {
        userDao.blockById(id);
        logger.info("User (id = {}) has been blocked", id);
    }

    public void unblockById(int id) throws DataBaseException {
        userDao.unblockById(id);
        logger.info("User (id = {}) has been unblocked", id);
    }

}

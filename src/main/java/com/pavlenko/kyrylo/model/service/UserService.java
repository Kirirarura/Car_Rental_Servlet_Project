package com.pavlenko.kyrylo.model.service;

import com.pavlenko.kyrylo.model.dao.UserDao;
import com.pavlenko.kyrylo.model.dto.UserDto;
import com.pavlenko.kyrylo.model.entity.Role;
import com.pavlenko.kyrylo.model.entity.User;
import com.pavlenko.kyrylo.model.entity.util.PasswordEncoder;
import com.pavlenko.kyrylo.model.exeption.AuthenticationException;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.exeption.EmailIsAlreadyRegisteredException;
import com.pavlenko.kyrylo.model.exeption.UserIsBlockedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Manages business logic related to the user.
 */
public class UserService {

    private final Logger logger = LogManager.getLogger(UserService.class);
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    public UserService(PasswordEncoder passwordEncoder, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    public User findById(Long id) throws DataBaseException {
        return userDao.findById(id);
    }

    public List<User> findAllUsers() throws DataBaseException {
        return userDao.findAll();
    }

    /**
     * Manages user authentication.
     * Checks if database contains combination of email and password.
     * If such combination found checks if user is blocked.
     *
     * @param email Input from guest that represents user email.
     * @param password Input from guest that represents user password.
     * @throws UserIsBlockedException Indicates that user is blocked.
     * @throws AuthenticationException Indicates that credentials was incorrect.
     * @throws DataBaseException Indicates that error occurred during database accessing.
     */
    public User authentication(String email, String password) throws UserIsBlockedException, AuthenticationException, DataBaseException {
        String encodedPass = passwordEncoder.encode(password);
        Optional<User> optionalUser = userDao.findByUsernameAndPassword(email, encodedPass);

        if (optionalUser.isPresent()){
            if (!optionalUser.get().isBlocked()){
                return optionalUser.get();
            } else {
                logger.warn("User ({}) is blocked", email);
                throw new UserIsBlockedException();
            }
        } else {
            logger.warn("The user credentials entered are incorrect");
            throw new AuthenticationException();
        }
    }

    /**
     * Manages registration of new customer.
     * Checks if email entered by guest is not present in the database.
     *
     * @param userDto An instance of userDto.
     * @throws EmailIsAlreadyRegisteredException Indicates that email is already used.
     * @throws DataBaseException Indicates that error occurred during database accessing.
     */
    public void registerNewAccount(UserDto userDto) throws EmailIsAlreadyRegisteredException, DataBaseException {
        checkEmailIsUnique(userDto.getEmail());
        User user = new User(userDto, Role.RoleEnum.CUSTOMER);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDao.create(user);
        logger.info("New user account {} has been created", user);
    }

    /**
     * Manages registration of new manager.
     * Checks if email entered by guest is not present in the database.
     *
     * @param userDto An instance of userDto.
     * @throws EmailIsAlreadyRegisteredException Indicates that email is already used.
     * @throws DataBaseException Indicates that error occurred during database accessing.
     */
    public void registerNewManagerAccount(UserDto userDto) throws EmailIsAlreadyRegisteredException, DataBaseException {
        checkEmailIsUnique(userDto.getEmail());
        User user = new User(userDto, Role.RoleEnum.MANAGER);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDao.create(user);
        logger.info("New manager account {} has been created", user);
    }

    /**
     * Manages checking if email is unique.
     */
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

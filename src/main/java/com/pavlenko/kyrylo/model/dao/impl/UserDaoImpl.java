package com.pavlenko.kyrylo.model.dao.impl;

import com.pavlenko.kyrylo.model.dao.UserDao;
import com.pavlenko.kyrylo.model.dao.impl.query.UserQueries;
import com.pavlenko.kyrylo.model.dao.impl.util.DBUtil;
import com.pavlenko.kyrylo.model.dao.mapper.UserMapper;
import com.pavlenko.kyrylo.model.entity.User;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDaoImpl implements UserDao {

    private final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private final UserMapper userMapper = new UserMapper();
    private final DataSource ds;
    private static final String ERROR_MASSAGE = "Error message: {}";

    public UserDaoImpl(DataSource dataSource) {
        this.ds = dataSource;
    }

    @Override
    public void create(User entity) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(UserQueries.CREATE_USER)) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());
            statement.setString(5, entity.getRole().getValue().name());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public User findById(Long id) throws DataBaseException {
        ResultSet rs = null;
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(UserQueries.FIND_BY_ID)) {
            statement.setInt(1, Math.toIntExact(id));
            rs = statement.executeQuery();
            User user = null;
            if (rs.next()){
                user = userMapper.extractFromResultSet(rs);
            }
            return user;
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        } finally {
            DBUtil.closeResources(rs);
        }
    }

    @Override
    public List<User> findAll() throws DataBaseException {
        try (Connection con = ds.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(UserQueries.FIND_ALL_USERS)) {
            List<User> userList = new ArrayList<>();


            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public void delete(long id) {
        throw new IllegalArgumentException();
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String email, String password) throws DataBaseException {
        ResultSet rs = null;
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(UserQueries.FIND_USER_BY_EMAIL_AND_PASSWORD)) {
            statement.setString(1, email);
            statement.setString(2, password);

            rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(userMapper.extractFromResultSet(rs));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        } finally {
            DBUtil.closeResources(rs);
        }
    }

    @Override
    public void blockById(int id) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(UserQueries.BLOCK_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public void unblockById(int id) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(UserQueries.UNBLOCK_BY_ID)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public boolean emailAlreadyExists(String email) throws DataBaseException {
        ResultSet rs = null;
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(UserQueries.FIND_BY_EMAIL)) {
            statement.setString(1, email);

            rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        } finally {
            DBUtil.closeResources(rs);
        }
    }
}

package com.pavlenko.kyrylo.model.dao.impl.util;

import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private DBUtil() {
        throw new IllegalStateException("Utility class");
    }
    private static final Logger logger = LogManager.getLogger(DBUtil.class);
    private static final String CLOSE_MESSAGES = "Can not close connection, resultSet or statement";

    public static void closeResources(Connection con, Statement...statements) throws DataBaseException {
        try {
            if (con != null){
                con.close();
            }
            for (Statement statement : statements) {
                if (statement != null) {
                    statement.close();
                }
            }
        } catch (SQLException e) {
            logger.error(CLOSE_MESSAGES, e);
            throw new DataBaseException();
        }
    }
    public static void closeResources(ResultSet resultSet) throws DataBaseException {
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(CLOSE_MESSAGES, e);
                throw new DataBaseException();
            }
        }
    }
}

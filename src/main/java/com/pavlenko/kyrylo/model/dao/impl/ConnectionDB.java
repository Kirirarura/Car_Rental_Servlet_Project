package com.pavlenko.kyrylo.model.dao.impl;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {

    private static final String DB_PROPERTIES = "E:/Java Projects/Car_Rental_Servlet_Project/src/main/resources/db.properties";
    private static final String DB_URL_KEY = "db.url";
    private static final String DB_USERNAME_KEY = "db.username";
    private static final String DB_PASSWORD_KEY = "db.password";
    private static volatile DataSource dataSource;

    private ConnectionDB() {
    }

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionDB.class) {
                if (dataSource == null) {
                    Properties props = new Properties();
                    FileInputStream fis;
                    MysqlDataSource ds;
                    try {
                        fis = new FileInputStream(DB_PROPERTIES);
                        props.load(fis);

                        ds = new MysqlConnectionPoolDataSource();
                        ds.setURL(props.getProperty(DB_URL_KEY));
                        ds.setUser(props.getProperty(DB_USERNAME_KEY));
                        ds.setPassword(props.getProperty(DB_PASSWORD_KEY));
                        dataSource = ds;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(java.sql.Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


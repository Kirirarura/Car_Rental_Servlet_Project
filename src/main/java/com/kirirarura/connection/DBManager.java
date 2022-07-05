package com.kirirarura.connection;

import com.kirirarura.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class DBManager {

    private static DBManager instance;

    private static final String INSERT_USER = "INSERT INTO user (firstname, secondname, email, password, role_id) " +
            "values (?, ?, ?, ?, ?)";
    private static final String SELECT_EMAIL_FROM_USERS = "SELECT email FROM user";
    private static final String SELECT_FROM_USERS = "SELECT * FROM user WHERE email = ? and password = ?";
    private DBManager() {}

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        } else {
            try {
                boolean connectionClosed = getConnection().isClosed();
                if (connectionClosed) {
                    instance = new DBManager();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Connection is closed: ", e);
            }
        }
        return instance;
    }
    public static Connection getConnection(){
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/mydbproject";
        String username = "root";
        String password = "12345";
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Connection error occurred.");
            e.printStackTrace();
        }
        return con;
    }
    public int registerUser(User user){
        boolean result = false;
        try(Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(INSERT_USER)){
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setInt(5, 1);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkEmail(String email){
        ArrayList<String> allEmails = new ArrayList<>();
        try(Connection con = getConnection();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_EMAIL_FROM_USERS)){
            while (resultSet.next()){
                if (resultSet.getString("email").contains(email)){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User logIn(String email, String password){
        User user = new User();
        try(Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(SELECT_FROM_USERS)){
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user.setFirstName(resultSet.getString("firstname"));
                user.setSecondName(resultSet.getString("secondname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(1);
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

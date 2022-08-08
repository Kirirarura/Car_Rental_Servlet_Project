package com.pavlenko.kyrylo.model.dao.impl.query;

public class UserQueries {


    public static final String CREATE_USER =
            "INSERT INTO users (" +
                    "firstname, lastname, email, password, role_id" +
                    ") values (?, ?, ?, ?, (SELECT id FROM roles WHERE rolename = ?))";

    public static final String FIND_ALL =
            "SELECT * FROM users " +
                    " INNER JOIN roles ON users.role_id=roles.id";
    public static final String FIND_USER_BY_EMAIL_AND_PASSWORD =
            FIND_ALL + " WHERE email = ? AND password = ?";

    public static final String FIND_ALL_USERS =
            FIND_ALL +
                    " WHERE rolename = 'customer'" +
                    " ORDER BY firstname";

    public static final String FIND_BY_EMAIL =
            FIND_ALL + " WHERE email = ?";

    public static final String BLOCK_BY_ID =
            "UPDATE users" +
                    " SET isblocked = 0" +
                    " WHERE id = ?";

    public static final String UNBLOCK_BY_ID =
            "UPDATE users" +
                    " SET isblocked = 1" +
                    " WHERE id = ?";
}

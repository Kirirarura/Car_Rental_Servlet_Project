package com.pavlenko.kyrylo.model.dao.impl.query;

public class UserQueries {

    private UserQueries() {
    }

    private static final String WHERE_USER_ID = " WHERE users.id = ?";

    public static final String CREATE_USER =
            "INSERT INTO users (" +
                    "firstname, lastname, email, password, roles_id" +
                    ") values (?, ?, ?, ?, (SELECT id FROM roles WHERE role_name = ?))";

    private static final String FIND_ALL =
            "SELECT * FROM users " +
                    " INNER JOIN roles ON users.roles_id=roles.id";
    public static final String FIND_USER_BY_EMAIL_AND_PASSWORD =
            FIND_ALL + " WHERE email = ? AND password = ?";

    public static final String FIND_ALL_USERS =
            FIND_ALL +
                    " WHERE role_name = 'customer'" +
                    " ORDER BY firstname";

    public static final String FIND_BY_EMAIL =
            FIND_ALL + " WHERE email = ?";

    public static final String FIND_BY_ID =
            FIND_ALL + WHERE_USER_ID;

    public static final String BLOCK_BY_ID =
            "UPDATE users" +
                    " SET is_blocked = 0" +
                    WHERE_USER_ID;

    public static final String UNBLOCK_BY_ID =
            "UPDATE users" +
                    " SET is_blocked = 1" +
                    WHERE_USER_ID;
}

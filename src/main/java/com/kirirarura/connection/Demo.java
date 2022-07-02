package com.kirirarura.connection;

import com.kirirarura.bean.User;

public class Demo {
    public static void main(String[] args) {
        DBManager dbManager = DBManager.getInstance();
        User user = dbManager.logIn("kyrylo.pavlenko41@gmail.com", "12345");
        System.out.println(user.getFirstName());
    }
}

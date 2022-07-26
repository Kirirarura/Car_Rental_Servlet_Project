package com.pavlenko.kyrylo.model.dao.impl;

import com.pavlenko.kyrylo.model.dao.DaoFactory;
import com.pavlenko.kyrylo.model.dao.UserDao;

public class DaoFactoryImpl extends DaoFactory {
    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl();
    }
}

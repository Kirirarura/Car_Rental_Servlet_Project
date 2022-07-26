package com.pavlenko.kyrylo.model.service;

import com.pavlenko.kyrylo.model.dao.impl.DaoFactoryImpl;

public class ServiceFactoryImpl extends ServiceFactory{
    @Override
    public UserService createUserService() {
        return new UserService(DaoFactoryImpl.getInstance().createUserDao());
    }

}

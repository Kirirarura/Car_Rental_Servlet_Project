package com.pavlenko.kyrylo.model.service;

public abstract class ServiceFactory {
    private static volatile ServiceFactory serviceFactory;

    protected ServiceFactory() {
    }

    public abstract UserService createUserService();

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            synchronized (ServiceFactory.class) {
                if (serviceFactory == null) {
                    serviceFactory = new ServiceFactoryImpl();
                }
            }
        }
        return serviceFactory;
    }
}

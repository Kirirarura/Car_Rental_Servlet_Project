package com.pavlenko.kyrylo.model.dao;

import com.pavlenko.kyrylo.model.dao.impl.DaoFactoryImpl;

import javax.sql.DataSource;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    protected DaoFactory() {
    }

    public abstract UserDao createUserDao(DataSource ds);

    public abstract CarDao createCarDao(DataSource ds);

    public abstract BookingDao createBookingDao(DataSource ds);
    public abstract QualityClassDao createQualityClassDao(DataSource ds);
    public abstract BrandDao createBrandDao(DataSource ds);

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactoryImpl();
        }
        return daoFactory;
    }
}

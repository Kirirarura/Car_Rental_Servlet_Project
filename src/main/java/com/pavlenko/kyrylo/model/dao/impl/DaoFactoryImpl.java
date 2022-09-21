package com.pavlenko.kyrylo.model.dao.impl;

import com.pavlenko.kyrylo.model.dao.*;

import javax.sql.DataSource;

public class DaoFactoryImpl extends DaoFactory {

    @Override
    public UserDao createUserDao(DataSource ds) {
        return new UserDaoImpl(ds);
    }

    @Override
    public CarDao createCarDao(DataSource ds) {
        return new CarDaoImpl(ds);
    }

    @Override
    public BookingDao createBookingDao(DataSource ds) {
        return new BookingDaoImpl(ds);
    }

    @Override
    public QualityClassDao createQualityClassDao(DataSource ds) {
        return new QualityClassDaoImpl(ds);
    }

    @Override
    public BrandDao createBrandDao(DataSource ds) {
        return new BrandDaoImpl(ds);
    }
}

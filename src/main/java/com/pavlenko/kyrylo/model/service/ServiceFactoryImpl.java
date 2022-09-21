package com.pavlenko.kyrylo.model.service;

import com.pavlenko.kyrylo.model.dao.DaoFactory;
import com.pavlenko.kyrylo.model.entity.util.Pbkdf2PasswordEncoder;

import javax.sql.DataSource;

public class ServiceFactoryImpl implements ServiceFactory{

    private final DataSource ds;

    public ServiceFactoryImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public UserService createUserService() {
        return new UserService(
                new Pbkdf2PasswordEncoder(),
                DaoFactory.getInstance().createUserDao(ds)
        );
    }

    @Override
    public CarService createCarService() {
        return new CarService(
                DaoFactory.getInstance().createCarDao(ds)
        );
    }

    @Override
    public BookingService createBookingService() {
        return new BookingService(
                DaoFactory.getInstance().createBookingDao(ds)
        );
    }

    @Override
    public QualityService createQualityService() {
        return new QualityService(
                DaoFactory.getInstance().createQualityClassDao(ds)
        );
    }

    @Override
    public BrandService createBrandService() {
        return new BrandService(
                DaoFactory.getInstance().createBrandDao(ds)
        );
    }
}

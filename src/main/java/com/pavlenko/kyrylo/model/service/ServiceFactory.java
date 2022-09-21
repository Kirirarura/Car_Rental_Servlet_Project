package com.pavlenko.kyrylo.model.service;


public interface ServiceFactory {

    UserService createUserService();

    CarService createCarService();

    BookingService createBookingService();

    QualityService createQualityService();

    BrandService createBrandService();
}

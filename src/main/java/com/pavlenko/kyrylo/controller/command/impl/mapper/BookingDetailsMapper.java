package com.pavlenko.kyrylo.controller.command.impl.mapper;

import com.pavlenko.kyrylo.model.dto.BookingDto;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Manages fetching data about booking from request.
 */
public class BookingDetailsMapper {
    private static final String PASSPORT_DATA = "passportData";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String CAR_PRICE = "carPrice";


    public BookingDto fetchUserDtoFromRequest(HttpServletRequest req, boolean withDriver, Car car, User user) {
        return new BookingDto(
                user,
                car,
                req.getParameter(PASSPORT_DATA),
                withDriver,
                req.getParameter(START_DATE),
                req.getParameter(END_DATE),
                req.getParameter(CAR_PRICE)
        );
    }
}

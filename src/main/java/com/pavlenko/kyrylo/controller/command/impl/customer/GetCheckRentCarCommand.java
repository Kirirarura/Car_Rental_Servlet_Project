package com.pavlenko.kyrylo.controller.command.impl.customer;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.command.impl.mapper.BookingDetailsMapper;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.validator.BookingValidator;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.dto.BookingDto;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.User;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.BookingService;
import com.pavlenko.kyrylo.model.service.CarService;
import com.pavlenko.kyrylo.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.*;

/**
 * Process car rent request.
 */
public class GetCheckRentCarCommand implements Command {

    private final BookingService bookingService;
    private final UserService userService;
    private final CarService carService;
    private final BookingDetailsMapper bookingDetailsMapper = new BookingDetailsMapper();

    public GetCheckRentCarCommand(BookingService bookingService, UserService userService, CarService carService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.carService = carService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        boolean withDriver = request.getParameter(WITH_DRIVER) != null;
        Long userId = (Long) request.getSession().getAttribute(USER_ID);
        Car car = (Car) request.getSession().getAttribute(CAR);
        BigDecimal carPrice = new BigDecimal(request.getParameter(CAR_PRICE));
        LocalDate startDate = LocalDate.parse(request.getParameter(START_DATE));
        LocalDate endDate = LocalDate.parse(request.getParameter(END_DATE));
        User user;

        try {
            user = userService.findById(userId);
            if (carService.checkCarStatusAvailable(car.getCarId())) {
                BigDecimal price = bookingService.calculatePrice(carPrice, startDate, endDate, withDriver);
                BookingDto bookingDto = bookingDetailsMapper.fetchUserDtoFromRequest(
                        request, withDriver, car, user, price);
                boolean isValid = BookingValidator.validate(bookingDto, request);
                if (isValid) {
                    request.getSession().setAttribute(BOOKING_DTO, bookingDto);
                    return JspFilePath.CUSTOMER_CHECK_RENT;
                }
            } else {
                request.setAttribute(STATUS, StatusesContainer.CAR_ALREADY_BOOKED_EXCEPTION);
            }
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.REGISTRATION_EXCEPTION);
        }
        return JspFilePath.CUSTOMER_RENT_CAR;
    }
}

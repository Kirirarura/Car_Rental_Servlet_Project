package com.pavlenko.kyrylo.controller.command.impl.customer;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.command.impl.mapper.BookingDetailsMapper;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.util.UriPath;
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

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

public class PostRentCarCommand implements Command {


    private final BookingService bookingService;
    private final UserService userService;
    private final CarService carService;
    private static final String WITH_DRIVER = "withDriver";
    private static final String USER_ID = "userId";
    private final BookingDetailsMapper bookingDetailsMapper = new BookingDetailsMapper();

    public PostRentCarCommand(BookingService bookingService, UserService userService, CarService carService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.carService = carService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        boolean withDriver = request.getParameter(WITH_DRIVER) != null;
        Long userId = (Long) request.getSession().getAttribute(USER_ID);
        Car car = (Car) request.getSession().getAttribute("car");
        User user;

        try {
            user = userService.findById(userId);
            if (carService.checkCarStatusAvailable(car.getCarId())) {
                BookingDto bookingDto = bookingDetailsMapper.fetchUserDtoFromRequest(request, withDriver, car, user);
                boolean isValid = BookingValidator.validate(bookingDto, request);
                if (isValid) {
                    bookingService.registerNewBooking(bookingDto);
                    return UriPath.REDIRECT + UriPath.CATALOG;
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

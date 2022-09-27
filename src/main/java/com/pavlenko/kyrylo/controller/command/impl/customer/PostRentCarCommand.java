package com.pavlenko.kyrylo.controller.command.impl.customer;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.dto.BookingDto;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.BookingService;
import com.pavlenko.kyrylo.model.service.CarService;

import javax.servlet.http.HttpServletRequest;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.BOOKING_DTO;
import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

public class PostRentCarCommand implements Command {

    private final BookingService bookingService;
    private final CarService carService;

    public PostRentCarCommand(BookingService bookingService, CarService carService) {
        this.bookingService = bookingService;
        this.carService = carService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        BookingDto bookingDto = (BookingDto) request.getSession().getAttribute(BOOKING_DTO);
        try {
            if (carService.checkCarStatusAvailable(bookingDto.getCar().getCarId())) {
                bookingService.registerNewBooking(bookingDto);
                return UriPath.REDIRECT + UriPath.CUSTOMER_REQUESTS;
            } else {
                request.setAttribute(STATUS, StatusesContainer.CAR_ALREADY_BOOKED_EXCEPTION);
            }
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.REGISTRATION_EXCEPTION);
        }
        return JspFilePath.CUSTOMER_CHECK_RENT;
    }
}

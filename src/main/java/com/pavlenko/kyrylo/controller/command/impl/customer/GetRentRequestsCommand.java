package com.pavlenko.kyrylo.controller.command.impl.customer;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.entity.Booking;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.BookingService;
import com.pavlenko.kyrylo.model.service.CarService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

/**
 * Returns page with all requests of log in user.
 */
public class GetRentRequestsCommand implements Command {

    BookingService bookingService;
    CarService carService;
    private static final String USER_ID = "userId";
    private static final String BOOKING_LIST = "bookingList";

    public GetRentRequestsCommand(BookingService bookingService, CarService carService) {
        this.bookingService = bookingService;
        this.carService = carService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        try {
            List<Booking> bookingList = bookingService.findByUserId((Long) request.getSession().getAttribute(USER_ID));
            request.setAttribute(BOOKING_LIST, bookingList);
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.BOOKING_INFO_LOADING_EXCEPTION);
        }

        return JspFilePath.CUSTOMER_REQUESTS;
    }
}

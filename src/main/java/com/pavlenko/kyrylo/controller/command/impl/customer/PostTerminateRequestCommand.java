package com.pavlenko.kyrylo.controller.command.impl.customer;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.BookingService;

import javax.servlet.http.HttpServletRequest;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.*;

public class PostTerminateRequestCommand implements Command {

    BookingService bookingService;
    public static final String BOOKING_ID = "bookingId";
    public static final String CAR_ID = "carId";

    public PostTerminateRequestCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long bookingId = Long.valueOf((request.getParameter(BOOKING_ID)));
        Long carId = Long.valueOf((request.getParameter(CAR_ID)));

        try {
            if (!bookingService.checkIfCanceled(bookingId)){
                bookingService.terminateRequestById(bookingId, carId);
            }
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.UPDATING_BOOKING_STATUS_EXCEPTION);
            return JspFilePath.CUSTOMER_REQUESTS;
        }

        return UriPath.REDIRECT + UriPath.CUSTOMER_REQUESTS;

    }
}

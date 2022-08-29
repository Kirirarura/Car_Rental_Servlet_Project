package com.pavlenko.kyrylo.controller.command.impl.customer;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.BookingService;

import javax.servlet.http.HttpServletRequest;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

public class PostAdditionalPaymentCommand implements Command {

    BookingService bookingService;

    private static final String BOOKING_ID = "bookingId";

    public PostAdditionalPaymentCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            bookingService.additionalPayment(Long.valueOf(request.getParameter(BOOKING_ID)));
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.FAILED_ADDITIONAL_PAYMENT_EXCEPTION);
        }
        return UriPath.REDIRECT + UriPath.CUSTOMER_REQUESTS;
    }
}

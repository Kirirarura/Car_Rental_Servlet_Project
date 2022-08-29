package com.pavlenko.kyrylo.controller.command.impl.manager;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.controller.validator.DeclineBookingValidator;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.BookingService;

import javax.servlet.http.HttpServletRequest;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

public class PostDeclineRequestCommand implements Command {

    BookingService bookingService;
    public static final String DECLINE_DESCRIPTION = "declineDescription";
    public static final String BOOKING_ID = "bookingId";
    public static final String CAR_ID = "carId";

    public PostDeclineRequestCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String declineDescription = request.getParameter(DECLINE_DESCRIPTION);
        Long bookingId = Long.valueOf(request.getParameter(BOOKING_ID));
        Long carId = Long.valueOf(request.getParameter(CAR_ID));

        boolean isValid = DeclineBookingValidator.validate(declineDescription, request);
        if (isValid){
            try {
                bookingService.declineRequest(bookingId, declineDescription, carId);
                return UriPath.REDIRECT + UriPath.MANAGER_MY_REQUESTS;
            } catch (DataBaseException e) {
                request.setAttribute(STATUS, StatusesContainer.FAILED_DECLINE_REQUEST_EXCEPTION);
            }
        }
        return JspFilePath.MANAGER_MY_REQUESTS;
    }

}

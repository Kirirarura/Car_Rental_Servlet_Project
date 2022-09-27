package com.pavlenko.kyrylo.controller.command.impl.manager;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.BookingService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.*;

/**
 * Process registering return.
 */
public class PostRegisterReturnCommand implements Command {

    BookingService bookingService;

    public PostRegisterReturnCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        boolean damaged = false;
        boolean returnedInTime = request.getParameter("returnInTime") != null;
        if (request.getParameter("damaged") != null){
            damaged = true;
        }

        Long carId = (Long) request.getSession().getAttribute(CAR_ID);
        Long bookingId = (Long) request.getSession().getAttribute(BOOKING_ID);
        try {
            bookingService.registerReturn(carId, bookingId, returnedInTime, damaged);
            request.getSession().removeAttribute(CAR_ID);
            request.getSession().removeAttribute(BOOKING_ID);
            return UriPath.REDIRECT + UriPath.MANAGER_MY_REQUESTS;
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.FAILED_REGISTER_RETURN_EXCEPTION);
        }
        return JspFilePath.MANAGER_MY_REQUESTS;
    }
}

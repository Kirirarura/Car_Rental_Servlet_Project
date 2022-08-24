package com.pavlenko.kyrylo.controller.command.impl.manager;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.entity.Booking;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.BookingService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

public class GetAllRequestsCommand implements Command {

    private static final String BOOKING_LIST = "bookingList";
    BookingService bookingService;

    public GetAllRequestsCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            List<Booking> bookingList = bookingService.findAllRequests();
            request.setAttribute(BOOKING_LIST, bookingList);
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.BOOKING_INFO_LOADING_EXCEPTION);
        }
        return JspFilePath.MANAGER_ALL_REQUESTS;
    }
}

package com.pavlenko.kyrylo.controller.command.impl.manager;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.BookingService;

import javax.servlet.http.HttpServletRequest;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.ID;
import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

/**
 * Process accepting request in case if everything with user data.
 */
public class PostAcceptRequestCommand implements Command {

    BookingService bookingService;

    public PostAcceptRequestCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter(ID));
        try {
            if(!bookingService.checkIfCanceled(id)){
                bookingService.updateBookingStatusId(id, 3L);
                return UriPath.REDIRECT + UriPath.MANAGER_MY_REQUESTS;
            } else {
                request.setAttribute(STATUS, StatusesContainer.FAILED_ACCEPT_REQUEST_EXCEPTION);
            }
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.FAILED_ACCEPT_REQUEST_EXCEPTION);
        }
        return JspFilePath.MANAGER_ERROR_PAGE;
    }
}

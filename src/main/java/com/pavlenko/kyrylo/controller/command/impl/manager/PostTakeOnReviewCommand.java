package com.pavlenko.kyrylo.controller.command.impl.manager;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.BookingService;

import javax.servlet.http.HttpServletRequest;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.*;

public class PostTakeOnReviewCommand implements Command {

    BookingService bookingService;
    private static final String USER_ID = "userId";
    public PostTakeOnReviewCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long bookingId = Long.valueOf(request.getParameter(ID));
        Long managerId = (Long) request.getSession().getAttribute(USER_ID);

        try {
            boolean firstCheck = bookingService.checkManagerOnRequest(bookingId);
            boolean secondCheck = bookingService.checkIfCanceled(bookingId);
            if(!firstCheck && !secondCheck){
                bookingService.takeOnReview(bookingId, managerId);
                return UriPath.REDIRECT + UriPath.MANAGER_MY_REQUESTS;
            } else {
                request.setAttribute(STATUS, StatusesContainer.BOOKING_ALREADY_ON_REVIEW_EXCEPTION);
            }
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.BOOKING_INFO_LOADING_EXCEPTION);
        }
        return JspFilePath.MANAGER_ERROR_PAGE;
    }
}

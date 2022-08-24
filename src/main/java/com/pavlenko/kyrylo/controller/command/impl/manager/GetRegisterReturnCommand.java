package com.pavlenko.kyrylo.controller.command.impl.manager;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;

import javax.servlet.http.HttpServletRequest;


public class GetRegisterReturnCommand implements Command {

    private static final String BOOKING_ID = "bookingId";
    public static final String CAR_ID = "carId";


    @Override
    public String execute(HttpServletRequest request) {
        Long bookingId = Long.valueOf(request.getParameter(BOOKING_ID));
        Long carId = Long.valueOf(request.getParameter(CAR_ID));
        request.getSession().setAttribute(BOOKING_ID, bookingId);
        request.getSession().setAttribute(CAR_ID, carId);
        return JspFilePath.MANAGER_REGISTER_RETURN;
    }
}

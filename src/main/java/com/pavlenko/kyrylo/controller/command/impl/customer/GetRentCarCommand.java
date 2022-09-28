package com.pavlenko.kyrylo.controller.command.impl.customer;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.CarService;

import javax.servlet.http.HttpServletRequest;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.*;

/**
 * Returns rent car page with form.
 */
public class GetRentCarCommand implements Command {

    private final CarService carService;

    public GetRentCarCommand(CarService carService) {
        this.carService = carService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            Car car = carService.findById(Long.valueOf(request.getParameter(ID)));
            request.getSession().setAttribute(CAR, car);
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.CAR_INFO_LOADING_EXCEPTION);
        }
        return JspFilePath.CUSTOMER_RENT_CAR;
    }
}

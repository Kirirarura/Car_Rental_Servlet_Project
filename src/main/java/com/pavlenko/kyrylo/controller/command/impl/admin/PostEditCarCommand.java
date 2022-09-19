package com.pavlenko.kyrylo.controller.command.impl.admin;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.controller.validator.EditCarValidator;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.CarService;

import javax.servlet.http.HttpServletRequest;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.*;

/**
 * Process editing a car.
 */
public class PostEditCarCommand implements Command {

    private final CarService carService;
    private static final String LABEL = "label";
    private static final String INPUT = "input";
    private static final String INPUT_ID = "inputID";
    public PostEditCarCommand(CarService carService) {
        this.carService = carService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        String label = request.getParameter(LABEL);
        String input = request.getParameter(INPUT);
        Long id = Long.valueOf(request.getParameter(ID));
        Long inputID = Long.valueOf(request.getParameter(INPUT_ID));

        request.getSession().setAttribute(ID, id);

        boolean isValid = true;
        if (!input.equals("empty")){
            isValid = EditCarValidator.validate(label, input, request);
        }
        if (isValid){
            try {
                carService.editCarInfo(label, input, id, inputID);
                request.getSession().removeAttribute(ID);
                request.getSession().removeAttribute("qualityClassList");
                request.getSession().removeAttribute("statusList");
                return UriPath.REDIRECT + UriPath.CATALOG;
            } catch (DataBaseException e) {
                request.setAttribute(STATUS, StatusesContainer.FAILED_EDIT_CAR_EXCEPTION);

            }
        }
        return JspFilePath.ADMIN_CAR_MANAGEMENT;
    }
}

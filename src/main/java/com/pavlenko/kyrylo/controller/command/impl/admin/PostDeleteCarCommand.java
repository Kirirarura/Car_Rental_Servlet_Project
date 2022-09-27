package com.pavlenko.kyrylo.controller.command.impl.admin;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.CarService;

import javax.servlet.http.HttpServletRequest;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.ID;

/**
 * Process deleting a car.
 */
public class PostDeleteCarCommand implements Command {
    private final CarService carService;

    public PostDeleteCarCommand(CarService carService) {
        this.carService = carService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter(ID));
        try {
            carService.deleteCarById(id);
        } catch (DataBaseException e) {
            return JspFilePath.CATALOG;
        }
        return UriPath.REDIRECT + UriPath.CATALOG;
    }
}

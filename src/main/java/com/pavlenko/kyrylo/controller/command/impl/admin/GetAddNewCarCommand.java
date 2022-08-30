package com.pavlenko.kyrylo.controller.command.impl.admin;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.entity.Quality;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.CarService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

/**
 * Returns add new car page.
 */
public class GetAddNewCarCommand implements Command {

    private final CarService carService;

    public GetAddNewCarCommand(CarService carService) {
        this.carService = carService;
    }

    /**
     * Loads Brand and Quality lists.
     * Returns add new car page.
     */
    @Override
    public String execute(HttpServletRequest request) {

        try {
            List<Brand> brandList = carService.findAllBrands();
            List<Quality> qualityClassList = carService.findAllQualityClasses();
            request.getSession().setAttribute("brandList", brandList);
            request.getSession().setAttribute("qualityClassList", qualityClassList);
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.CAR_INFO_LOADING_EXCEPTION);
        }
        return JspFilePath.ADMIN_ADD_NEW_CAR;
    }
}

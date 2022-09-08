package com.pavlenko.kyrylo.controller.command.impl.admin;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.CarStatus;
import com.pavlenko.kyrylo.model.entity.Quality;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.CarService;
import com.pavlenko.kyrylo.model.service.QualityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.ID;
import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;


/**
 * Returns editing car page
 */
public class GetEditCarCommand implements Command {

    private final CarService carService;
    private final QualityService qualityService;

    public GetEditCarCommand(CarService carService, QualityService qualityService) {
        this.carService = carService;
        this.qualityService = qualityService;
    }

    /**
     * Loads car, car status and quality lists.
     */
    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter(ID));
        try {
            Car car = carService.findById(id);
            List<Quality> qualityClassList = qualityService.findAllQualityClasses();
            List<CarStatus> statusList = carService.findAllStatuses();

            request.getSession().setAttribute("car", car);
            request.getSession().setAttribute("qualityClassList", qualityClassList);
            request.getSession().setAttribute("statusList", statusList);
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.CAR_INFO_LOADING_EXCEPTION);
        }

        return JspFilePath.ADMIN_CAR_MANAGEMENT;
    }
}

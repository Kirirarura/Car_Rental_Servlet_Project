package com.pavlenko.kyrylo.controller.command.impl.admin;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.command.impl.mapper.CarDetailsMapper;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.controller.validator.AddCarValidator;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.dto.CarDto;
import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.entity.Quality;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.CarService;

import javax.servlet.http.HttpServletRequest;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

/**
 * Process adding new car.
 */
public class PostAddNewCarCommand implements Command {

    private final CarService carService;
    private final CarDetailsMapper carDetailsMapper = new CarDetailsMapper();
    private static final String BRAND = "brandId";
    private static final String QUALITY = "qualityId";
    public PostAddNewCarCommand(CarService carService) {
        this.carService = carService;
    }

    /**
     * Validates carDto. Checks if craDto valid.
     * If carDto valid registration continues.
     */
    @Override
    public String execute(HttpServletRequest request) {

        Long brandId = Long.valueOf(request.getParameter(BRAND));
        Long qualityId = Long.valueOf(request.getParameter(QUALITY));

        try {
            Brand brand = carService.findBrandById(brandId);
            Quality quality = carService.findQualityById(qualityId);
            CarDto carDto = carDetailsMapper.fetchUserDtoFromRequest(request, brand, quality);
            boolean isValid = AddCarValidator.validate(carDto, request);
            if (isValid) {
                carService.registerNewCar(carDto);
                return UriPath.REDIRECT + UriPath.CATALOG;
            }
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.FAILED_ADD_CAR_EXCEPTION);
        }
        return JspFilePath.ADMIN_ADD_NEW_CAR;
    }
}

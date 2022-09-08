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
import com.pavlenko.kyrylo.model.service.BrandService;
import com.pavlenko.kyrylo.model.service.CarService;
import com.pavlenko.kyrylo.model.service.QualityService;

import javax.servlet.http.HttpServletRequest;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

/**
 * Process adding new car.
 */
public class PostAddNewCarCommand implements Command {

    private final CarService carService;
    private final QualityService qualityService;
    private final BrandService brandService;
    private final CarDetailsMapper carDetailsMapper = new CarDetailsMapper();
    private static final String BRAND = "brandId";
    private static final String QUALITY = "qualityId";
    public PostAddNewCarCommand(CarService carService, QualityService qualityService, BrandService brandService) {
        this.carService = carService;
        this.qualityService = qualityService;
        this.brandService = brandService;
    }

    /**
     * Validates carDto. Checks if craDto valid.
     * If carDto valid registration continues.
     */
    @Override
    public String execute(HttpServletRequest request) {

        Long brandId = Long.valueOf(request.getParameter(BRAND));
        Long qualityId = Long.valueOf(request.getParameter(QUALITY));
        CarDto carDto = null;
        try {
            Brand brand = brandService.findBrandById(brandId);
            Quality quality = qualityService.findQualityById(qualityId);
            carDto = carDetailsMapper.fetchUserDtoFromRequest(request, brand, quality);
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.FAILED_ADD_CAR_EXCEPTION);
        }

        boolean isValid = AddCarValidator.validate(carDto, request);
        if (isValid) {
            try {
                carService.registerNewCar(carDto);
            } catch (DataBaseException e) {
                request.setAttribute(STATUS, StatusesContainer.FAILED_ADD_CAR_EXCEPTION);
            }
            return UriPath.REDIRECT + UriPath.CATALOG;
        }
        return JspFilePath.ADMIN_ADD_NEW_CAR;
    }
}

package com.pavlenko.kyrylo.controller.command.impl.common;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.command.impl.mapper.CatalogMapper;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.Quality;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.CarService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

public class GetCatalogCommand implements Command {

    private final CarService carService;
    private final CatalogMapper catalogMapper = new CatalogMapper();
    private static final String CAR_LIST = "carList";
    private static final String PRICE = "price";
    private static final String NAME = "name";
    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final String ROLE_ATTRIBUTE = "role";

    public GetCatalogCommand(CarService carService) {
        this.carService = carService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        Map<String, String> filterParam = catalogMapper.fetchFilterParametersFromRequest(request);
        String sort = "empty";
        String order = "empty";
        if (request.getParameter(PRICE) != null){
            sort = PRICE;
        } else if (request.getParameter(NAME) != null){
            sort = NAME;
        }
        if (request.getParameter(ASC) != null){
            order = ASC;
        } else if (request.getParameter(DESC) != null){
            order = DESC;
        }
        String role = (String) request.getSession().getAttribute(ROLE_ATTRIBUTE);
        try {
            List<Car> carList = carService.findAllCars(filterParam, role, sort, order);
            List<Brand> brandList = carService.findAllBrands();
            List<Quality> qualityClassList = carService.findAllQualityClasses();
            request.getSession().setAttribute("brandList", brandList);
            request.getSession().setAttribute("qualityClassList", qualityClassList);
            request.setAttribute(CAR_LIST, carList);
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.CAR_INFO_LOADING_EXCEPTION);
        }

        return JspFilePath.CATALOG;
    }


}

package com.pavlenko.kyrylo.controller.command.impl.mapper;

import com.pavlenko.kyrylo.model.dto.CarDto;
import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.entity.Quality;

import javax.servlet.http.HttpServletRequest;

public class CarDetailsMapper {

    private static final String MODEL = "model";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";


    public CarDto fetchUserDtoFromRequest(HttpServletRequest req, Brand brand, Quality quality) {
        return new CarDto(
                brand,
                req.getParameter(MODEL),
                req.getParameter(PRICE),
                quality,
                req.getParameter(DESCRIPTION)
        );
    }
}

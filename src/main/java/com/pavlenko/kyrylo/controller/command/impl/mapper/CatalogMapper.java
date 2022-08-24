package com.pavlenko.kyrylo.controller.command.impl.mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CatalogMapper {
    public Map<String, String> fetchFilterParametersFromRequest(HttpServletRequest request) {
        Map<String, String> filterParameters = new HashMap<>();
        String brand;
        String quality;
        if (request.getParameter("searchOneBrand") != null) {
            brand = request.getParameter("brandValue");
            filterParameters.put("Brand", brand);
        }
        if (request.getParameter("searchOneQuality") != null) {
            quality = request.getParameter("qualityValue");
            filterParameters.put("Quality", quality);
        }

        return filterParameters;

    }
}

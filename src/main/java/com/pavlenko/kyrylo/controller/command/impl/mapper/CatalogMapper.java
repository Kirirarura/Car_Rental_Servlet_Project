package com.pavlenko.kyrylo.controller.command.impl.mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages fetching filter parameters from request.
 */
public class CatalogMapper {

    private static final String SORT = "Sort";
    private static final String PRICE = "price";
    private static final String MODEL = "model_name";
    private static final String ORDER = "Order";
    private static final String ASC = "ASC";
    private static final String DESC = "DESC";

    private static final String PAGE = "page";
    private static final String ACTIVE_PAGE_NUMBER = "activePageNumber";
    private static final Integer START_PAGE_NUMBER = 1;

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
        if (request.getParameter(PRICE) != null){
            filterParameters.put(SORT, PRICE);
        } else if (request.getParameter("name") != null){
            filterParameters.put(SORT, MODEL);
        }
        if (request.getParameter(ASC) != null){
            filterParameters.put(ORDER, ASC);
        } else if (request.getParameter(DESC) != null){
            filterParameters.put(ORDER, DESC);
        }
        return filterParameters;
    }

    public void insertInfoIntoRequest(Map<String, String> filterParameters, HttpServletRequest req) {
        String pageNumber = req.getParameter(PAGE);

        if (pageNumber != null && !pageNumber.isEmpty()) {
            req.setAttribute(ACTIVE_PAGE_NUMBER, Integer.parseInt(pageNumber));
        } else {
            req.setAttribute(ACTIVE_PAGE_NUMBER, START_PAGE_NUMBER);
        }

        for (Map.Entry<String, String> entry : filterParameters.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }
    }
}

package com.pavlenko.kyrylo.model.dao.impl.query;

import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.entity.Quality;

import java.util.Map;

public class CatalogQueryBuilder {

    private static final String WHERE = " WHERE";
    private static final String AND = " AND";
    private static final String ORDER_BY = " ORDER BY %s";
    private static final String BRAND_TYPE = " brand_id = (SELECT id FROM brands WHERE brand_name = '%s')";
    private static final String QUALITY_TYPE =
            " quality_class_id = (SELECT id FROM quality_class WHERE quality_class_name = '%s')";
    private static final String STATUS_AVAILABLE = " car_status_name = 'AVAILABLE'";

    private CatalogQueryBuilder() {
    }

    public static String buildCarQueryFilterForFindAll(Map<String, String> filterFieldMap, boolean adminRequest) {
        String result = null;
        String condition;
        if (filterFieldMap.isEmpty()) {
            if (!adminRequest){
                result = CarQueries.FIND_ALL_FROM_CARS + WHERE + STATUS_AVAILABLE;
            } else {
                result = CarQueries.FIND_ALL_FROM_CARS;
            }
        } else {
            condition = buildFullCondition(filterFieldMap, adminRequest);
            if (condition.length() > 0) {
                result = CarQueries.FIND_ALL_FROM_CARS + condition;
            }
        }
        return result;
    }
    public static String buildCarQueryFilterForFindAllCount(Map<String, String> filterFieldMap, boolean adminRequest) {
        String result = null;
        String condition;
        if (filterFieldMap.isEmpty()) {
            if (!adminRequest){
                result = CarQueries.FIND_ALL_COUNT_FROM_CARS + WHERE + STATUS_AVAILABLE;
            } else {
                result = CarQueries.FIND_ALL_COUNT_FROM_CARS;
            }
        } else {
            condition = buildFullCondition(filterFieldMap, adminRequest);
            if (condition.length() > 0) {
                result = CarQueries.FIND_ALL_COUNT_FROM_CARS + condition;
            }
        }
        return result;
    }

    private static String buildFullCondition(Map<String, String> filterFieldMap, boolean adminRequest) {
        StringBuilder conditions = new StringBuilder();

        buildBrandIdCondition(conditions, filterFieldMap);
        buildQualityIdCondition(conditions, filterFieldMap);
        buildAvailableCondition(conditions, adminRequest);
        buildSortCondition(conditions, filterFieldMap);
        buildOrderCondition(conditions, filterFieldMap);

        return conditions.toString();
    }

    private static void buildBrandIdCondition(StringBuilder conditions, Map<String, String> filterFieldMap) {
        StringBuilder condition = new StringBuilder();
        for (Brand.BrandEnum brandEnum : Brand.BrandEnum.values()) {
            if (filterFieldMap.containsValue(brandEnum.name())) {
                condition.append(WHERE);
                condition.append(String.format(BRAND_TYPE, brandEnum.name()));
            }
        }
        conditions.append(condition);
    }

    private static void buildQualityIdCondition(StringBuilder conditions, Map<String, String> filterFieldMap) {
        StringBuilder condition = new StringBuilder();
        for (Quality.QualityEnum qualityEnum : Quality.QualityEnum.values()) {
            if (filterFieldMap.containsValue(qualityEnum.name())) {
                condition.append(String.format(QUALITY_TYPE, qualityEnum.name()));
            }
        }
        if (conditionIsNotEmpty(conditions) && conditionIsNotEmpty(condition)) {
            conditions.append(AND).append(condition);
        }
        if (!conditionIsNotEmpty(conditions) && conditionIsNotEmpty(condition)){
            conditions.append(WHERE).append(condition);
        }
    }

    private static void buildAvailableCondition(StringBuilder conditions, boolean adminRequest){
        if (!adminRequest){
            StringBuilder condition;
            if (conditions.length() == 0) {
                condition = new StringBuilder(WHERE + STATUS_AVAILABLE);
            } else {
                condition = new StringBuilder(AND + STATUS_AVAILABLE);
            }
            conditions.append(condition);
        }
    }

    private static void buildSortCondition(StringBuilder conditions, Map<String, String> filterFieldMap) {
        StringBuilder condition = new StringBuilder();
        if (filterFieldMap.get("Sort") != null){
            String addition = String.format(ORDER_BY, filterFieldMap.get("Sort"));
            condition.append(addition);
        }
        conditions.append(condition);
    }
    private static void buildOrderCondition(StringBuilder conditions, Map<String, String> filterFieldMap) {
        StringBuilder condition = new StringBuilder();
        if (filterFieldMap.get("Order") != null){
            String addition = filterFieldMap.get("Order");
            condition.append(" ").append(addition);
        }
        conditions.append(condition);
    }



    private static boolean conditionIsNotEmpty(StringBuilder condition) {
        return condition.length() > 0;
    }

}

package com.pavlenko.kyrylo.model.dao.impl.query;

import com.pavlenko.kyrylo.model.dao.impl.CarDaoImpl;
import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.entity.Quality;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class CatalogQueryBuilder {

    private static final Logger logger = LogManager.getLogger(CatalogQueryBuilder.class);
    private static final String WHERE = " WHERE";
    private static final String AND = " AND";
    private static final String BRAND_TYPE = " brand_id = (SELECT id FROM brands WHERE brand_name = '%s')";
    private static final String QUALITY_TYPE =
            " quality_class_id = (SELECT id FROM quality_class WHERE quality_class_name = '%s')";
    private static final String STATUS_AVAILABLE = " car_status_name = 'AVAILABLE'";

    private CatalogQueryBuilder() {
    }

    public static String buildCarQueryFilterForFindAll(Map<String, String> filterFieldMap, boolean adminRequest) {
        String result = null;
        String condition = "";
        if (filterFieldMap.isEmpty()) {
            result = CarQueries.FIND_ALL_FROM_CARS;
        } else {
            condition = buildFullCondition(filterFieldMap);
            if (condition.length() > 0) {
                result = CarQueries.FIND_ALL_FROM_CARS + WHERE + condition;
            }
        }
        if (adminRequest) {
            return result;
        } else {
            if (condition.length() == 0) {
                result = result + WHERE + STATUS_AVAILABLE;
            } else {
                result = result + AND + STATUS_AVAILABLE;
            }
        }
        return result;
    }

    private static String buildFullCondition(Map<String, String> filterFieldMap) {
        StringBuilder conditions = new StringBuilder();

        buildBrandIdCondition(conditions, filterFieldMap);
        buildQualityIdCondition(conditions, filterFieldMap);

        return conditions.toString();
    }

    private static void buildBrandIdCondition(StringBuilder conditions, Map<String, String> filterFieldMap) {
        StringBuilder condition = new StringBuilder();
        for (Brand.BrandEnum brandEnum : Brand.BrandEnum.values()) {
            if (filterFieldMap.containsValue(brandEnum.name())) {
                condition = new StringBuilder(String.format(BRAND_TYPE, brandEnum.name()));
            }
        }
        conditions.append(condition);
    }

    private static void buildQualityIdCondition(StringBuilder conditions, Map<String, String> filterFieldMap) {
        StringBuilder condition = new StringBuilder();
        for (Quality.QualityEnum qualityEnum : Quality.QualityEnum.values()) {
            if (filterFieldMap.containsValue(qualityEnum.name())) {
                String addition = String.format(QUALITY_TYPE, qualityEnum.name());
                condition.append(addition);
            }
        }
        if (conditionIsNotEmpty(conditions) && conditionIsNotEmpty(condition)) {
            conditions.append(AND).append(condition);
        } else {
            conditions.append(condition);
        }
    }

    private static boolean conditionIsNotEmpty(StringBuilder condition) {
        return condition.length() > 0;
    }

}

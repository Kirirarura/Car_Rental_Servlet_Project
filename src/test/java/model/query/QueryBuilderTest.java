package model.query;

import com.pavlenko.kyrylo.model.dao.impl.query.CatalogQueryBuilder;
import com.pavlenko.kyrylo.model.entity.Brand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


class QueryBuilderTest {

    private final Map<String, String> filterFieldMap = new HashMap<>();

    private static final String FIND_ALL_FROM_CARS =
            "SELECT * FROM cars" +
            " INNER JOIN brands ON cars.brand_id = brands.id" +
            " INNER JOIN quality_class ON cars.quality_class_id = quality_class.id" +
            " INNER JOIN car_status ON cars.car_status_id = car_status.id";
    private static final String STATUS_AVAILABLE = " car_status_name = 'AVAILABLE'";
    private static final String WHERE =  " WHERE";
    private static final String AND =  " AND";
    private static final String BRAND_TYPE = " brand_id = (SELECT id FROM brands WHERE brand_name = 'BMW')";
    private static final String QUALITY_TYPE =
            " quality_class_id = (SELECT id FROM quality_class WHERE quality_class_name = 'NEW')";
    private static final String FIND_ALL_FROM_CARS_AVAILABLE =
            FIND_ALL_FROM_CARS + WHERE + STATUS_AVAILABLE ;
    private static final String FIND_ALL_FROM_CARS_AVAILABLE_BRAND =
            FIND_ALL_FROM_CARS + WHERE + BRAND_TYPE + AND + STATUS_AVAILABLE;
    private static final String FIND_ALL_FROM_CARS_AVAILABLE_QUALITY =
            FIND_ALL_FROM_CARS + WHERE + QUALITY_TYPE + AND + STATUS_AVAILABLE;
    private static final String FIND_ALL_FROM_CARS_AVAILABLE_QUALITY_AND_BRAND =
            FIND_ALL_FROM_CARS + WHERE + BRAND_TYPE + AND + QUALITY_TYPE +
                    AND + STATUS_AVAILABLE;


    @BeforeEach
    void init(){
        filterFieldMap.clear();
    }

    @Test
    void testQueryBuildingWithoutFiltersForAdmin(){
        String query = CatalogQueryBuilder.buildCarQueryFilterForFindAll(filterFieldMap, true);
        Assertions.assertEquals(FIND_ALL_FROM_CARS, query);
    }

    @Test
    void testQueryBuildingWithoutFiltersForCustomer(){
        String query = CatalogQueryBuilder.buildCarQueryFilterForFindAll(filterFieldMap, false);
        Assertions.assertEquals(FIND_ALL_FROM_CARS_AVAILABLE, query);
    }

    @Test
    void testQueryBuildingWithFiltersBrands(){
        String brand = "BMW";
        filterFieldMap.put("Brand", brand);
        String query = CatalogQueryBuilder.buildCarQueryFilterForFindAll(filterFieldMap, false);
        Assertions.assertEquals(FIND_ALL_FROM_CARS_AVAILABLE_BRAND, query);
    }

    @Test
    void testQueryBuildingWithFiltersQuality(){
        String quality = "NEW";
        filterFieldMap.put("Quality", quality);
        String query = CatalogQueryBuilder.buildCarQueryFilterForFindAll(filterFieldMap, false);
        Assertions.assertEquals(FIND_ALL_FROM_CARS_AVAILABLE_QUALITY, query);
    }

    @Test
    void testQueryBuildingWithFiltersQualityAndBrand(){
        String quality = "NEW";
        String brand = "BMW";
        filterFieldMap.put("Quality", quality);
        filterFieldMap.put("Brand", brand);
        String query = CatalogQueryBuilder.buildCarQueryFilterForFindAll(filterFieldMap, false);
        Assertions.assertEquals(FIND_ALL_FROM_CARS_AVAILABLE_QUALITY_AND_BRAND, query);
    }


    @Test
    void testSqlInjection(){
        String brand = " getRequestString(UserId);";
        filterFieldMap.put("Brand", brand);
        String query = CatalogQueryBuilder.buildCarQueryFilterForFindAll(filterFieldMap, false);
        Assertions.assertNotEquals(FIND_ALL_FROM_CARS_AVAILABLE_BRAND, query);
    }
}

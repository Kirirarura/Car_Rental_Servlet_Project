package com.pavlenko.kyrylo.model.dao.impl.query;

public class CarQueries {

    private CarQueries() {}

    private static final String UPDATE_CARS = "UPDATE cars";
    private static final String WHERE_ID = " WHERE c_id = ?";

    public static final String CREATE_CAR =
            "INSERT INTO cars (" +
                    "model_name, price, brand_id, quality_class_id, description" +
                    ") values (?, ?, " +
                    "(SELECT id FROM brands WHERE brand_name = ?), " +
                    "(SELECT id FROM quality_class WHERE quality_class_name = ?)," +
                    "?)";

    public static final String FIND_ALL_FROM_CARS =
            "SELECT * FROM cars " +
                    " INNER JOIN brands ON cars.brand_id = brands.id" +
                    " INNER JOIN quality_class ON cars.quality_class_id = quality_class.id" +
                    " INNER JOIN car_status ON cars.car_status_id = car_status.id";

    public static final String FIND_ALL_CARS_AVAILABLE =
                    " WHERE car_status_name = 'AVAILABLE'";

    public static final String FIND_CAR_BY_ID =
            FIND_ALL_FROM_CARS + WHERE_ID;

    public static final String FIND_ALL_QUALITY_CLASSES =
            "SELECT * FROM quality_class";

    public static final String FIND_QUALITY_CLASS_BY_ID =
            FIND_ALL_QUALITY_CLASSES +
                    " WHERE id = ?";

    public static final String FIND_ALL_CAR_STATUSES =
            "SELECT * FROM car_status";

    public static final String FIND_ALL_BRANDS =
            "SELECT * FROM brands";

    public static final String FIND_BRAND_BY_ID =
            FIND_ALL_BRANDS +
                    " WHERE id = ?";

    public static final String UPDATE_CAR_PRICE =
             UPDATE_CARS +
                    " SET price = ?" +
                    WHERE_ID;

    public static final String UPDATE_CAR_QUALITY =
            UPDATE_CARS +
                    " SET quality_class_id = ?" +
                    WHERE_ID;
    public static final String UPDATE_CAR_STATUS =
            UPDATE_CARS +
                    " SET car_status_id = ?" +
                    WHERE_ID;
    public static final String UPDATE_CAR_DESCRIPTION =
            UPDATE_CARS +
                    " SET description = ?" +
                    WHERE_ID;

    public static final String DELETE_CAR_BY_ID =
            "DELETE FROM cars" +
                    WHERE_ID;

    public static final String SELECT_CAR_STATUS =
            "SELECT car_status_id FROM cars" +
                    WHERE_ID;
}

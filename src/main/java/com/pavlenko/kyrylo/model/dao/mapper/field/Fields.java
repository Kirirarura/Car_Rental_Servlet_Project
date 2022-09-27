package com.pavlenko.kyrylo.model.dao.mapper.field;

public class Fields {
    private Fields() {
    }


    // ----- User --------

    public static final String USER_ID = "id";
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastname";
    public static final String EMAIL = "email";
    public static final String IS_BLOCKED = "is_blocked";
    public static final String USER_ROLE_ID = "roles_id";


    // ----- Role --------

    public static final String ROLE_ID = "id";
    public static final String ROLE = "role_name";


    // ----- Car --------

    public static final String CAR_ID = "id";
    public static final String MODEL_NAME = "model_name";
    public static final String PRICE = "price";
    public static final String CAR_BRAND_ID = "brand_id";
    public static final String CAR_QUALITY_ID = "quality_class_id";
    public static final String CAR_CAR_STATUS_ID = "car_status_id";
    public static final String DESCRIPTION_EN = "description_en";
    public static final String DESCRIPTION_UA = "description_ua";


    // ----- Brand --------

    public static final String BRAND_ID = "id";
    public static final String BRAND = "brand_name";


    // ----- Quality --------

    public static final String QUALITY_ID = "id";
    public static final String QUALITY = "quality_class_name";


    // ----- CarStatus --------

    public static final String CAR_STATUS_ID = "id";
    public static final String CAR_STATUS = "car_status_name";


    // ----- Booking --------

    public static final String BOOKING_ID = "id";
    public static final String BOOKING_USER_ID = "user_id";
    public static final String BOOKING_STATUS_ID = "status_id";
    public static final String BOOKING_CAR_ID = "car_id";
    public static final String BOOKING_STATUS = "booking_status_name";
    public static final String BOOKING_USER_DETAILS = "user_details";
    public static final String BOOKING_WITH_DRIVER = "with_driver";
    public static final String BOOKING_START_DATE = "booking_start_date";
    public static final String BOOKING_END_DATE = "booking_end_date";
    public static final String BOOKING_PRICE = "price";
    public static final String BOOKING_DECLINE_INFO = "decline_info";
    public static final String BOOKING_ADDITIONAL_FEE = "additional_fee";

}

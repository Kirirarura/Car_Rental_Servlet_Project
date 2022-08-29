package com.pavlenko.kyrylo.model.dao.impl.query;

public class BookingQueries {

    private BookingQueries() {
    }

    private static final String WHERE_ID = " WHERE b_id = ?";
    private static final String UPDATE = "UPDATE booking";
    private static final String FIND_ALL =
            "SELECT * FROM booking" +
                    " INNER JOIN booking_status ON booking.status_id = booking_status.id" +
                    " INNER JOIN users ON booking.user_id = users.u_id" +
                    " INNER JOIN roles ON users.roles_id=roles.id" +
                    " INNER JOIN cars ON booking.car_id = cars.c_id" +
                    " INNER JOIN brands ON cars.brand_id = brands.id" +
                    " INNER JOIN quality_class ON cars.quality_class_id = quality_class.id" +
                    " INNER JOIN car_status ON cars.car_status_id = car_status.id";

    public static final String FIND_ALL_PENDING_REVIEW =
            FIND_ALL + " WHERE booking_status_name = 'PENDING_REVIEW'";

    public static final String FIND_ALL_BY_MANAGER_ID =
            FIND_ALL + " WHERE manager_id = ?";


    public static final String CREATE_BOOKING =
            "INSERT INTO booking (" +
                    " user_id,  car_id, user_details, with_driver, booking_start_date, booking_end_date, price)" +
                    " values (?, ?, ?, ?, ?, ?, ?)";


    public static final String FIND_STATUS =
            "SELECT status_id FROM booking" +
                    WHERE_ID;
    public static final String FIND_BY_USER_ID =
            FIND_ALL + " WHERE user_id = ?" +
                    " ORDER BY creation_date DESC";

    public static final String TERMINATE_REQUEST_BY_ID =
            UPDATE +
                    " SET status_id = 6" +
                    WHERE_ID;

    public static final String TAKE_ON_REVIEW =
            UPDATE +
                    " SET manager_id = ?," +
                    " status_id = 2" +
                    WHERE_ID;

    public static final String UPDATE_BOOKING_STATUS =
            UPDATE +
                    " SET status_id = ?" +
                    WHERE_ID;

    public static final String REGISTER_RETURN =
            UPDATE +
                    " SET status_id = 4," +
                    " additional_fee = ?" +
                    WHERE_ID;

    public static final String ADD_DECLINE_INFO =
            UPDATE +
                    " SET decline_info = ?" +
                    WHERE_ID;

    public static final String UPDATE_ADDITIONAL_FEE =
            UPDATE +
                    " SET additional_fee = ?" +
                    WHERE_ID;
}

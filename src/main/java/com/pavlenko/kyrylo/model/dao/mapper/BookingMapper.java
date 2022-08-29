package com.pavlenko.kyrylo.model.dao.mapper;

import com.pavlenko.kyrylo.model.dao.mapper.field.Fields;
import com.pavlenko.kyrylo.model.entity.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookingMapper {

    private final BookingStatusMapper bookingStatusMapper = new BookingStatusMapper();
    private final UserMapper userMapper = new UserMapper();
    private final CarMapper carMapper = new CarMapper();

    public Booking extractFromResultSet(ResultSet rs) throws SQLException {
        return Booking.builder()
                .id(rs.getLong(Fields.ID))
                .user(userMapper.extractFromResultSet(rs))
                .bookingStatus(bookingStatusMapper.extractFromResultSet(rs))
                .car(carMapper.extractFromResultSet(rs))
                .userDetails(rs.getString(Fields.BOOKING_USER_DETAILS))
                .withDriver(rs.getInt(Fields.BOOKING_WITH_DRIVER))
                .startDate(LocalDate.parse(rs.getString(Fields.BOOKING_START_DATE)))
                .endDate(LocalDate.parse(rs.getString(Fields.BOOKING_END_DATE)))
                .price(rs.getBigDecimal(Fields.BOOKING_PRICE))
                .declineInfo(rs.getString(Fields.BOOKING_DECLINE_INFO))
                .additionalFee(rs.getBigDecimal(Fields.BOOKING_ADDITIONAL_FEE))
                .build();
    }
}

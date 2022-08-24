package com.pavlenko.kyrylo.model.dao.mapper;

import com.pavlenko.kyrylo.model.dao.mapper.field.Fields;
import com.pavlenko.kyrylo.model.entity.BookingStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingStatusMapper {

    public BookingStatus extractFromResultSet(ResultSet rs) throws SQLException {
        return new BookingStatus(
                rs.getLong(Fields.CAR_STATUS_ID),
                BookingStatus.BookingEnum.valueOf(rs.getString(Fields.BOOKING_STATUS))
        );
    }
}

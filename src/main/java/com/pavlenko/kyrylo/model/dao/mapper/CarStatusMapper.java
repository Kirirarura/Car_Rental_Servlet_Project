package com.pavlenko.kyrylo.model.dao.mapper;

import com.pavlenko.kyrylo.model.dao.mapper.field.Fields;
import com.pavlenko.kyrylo.model.entity.CarStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarStatusMapper {
    public CarStatus extractFromResultSet(ResultSet rs) throws SQLException {
        return new CarStatus(
                rs.getLong(Fields.CAR_STATUS_ID),
                CarStatus.CarStatusEnum.valueOf(rs.getString(Fields.CAR_STATUS))
        );
    }
}

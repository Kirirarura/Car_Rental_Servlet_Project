package com.pavlenko.kyrylo.model.dao.mapper;

import com.pavlenko.kyrylo.model.dao.mapper.field.Fields;
import com.pavlenko.kyrylo.model.entity.Quality;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QualityMapper {

    public Quality extractFromResultSet(ResultSet rs) throws SQLException {
        return new Quality(
                rs.getLong(Fields.QUALITY_ID),
                Quality.QualityEnum.valueOf(rs.getString(Fields.QUALITY))
        );
    }
}

package com.pavlenko.kyrylo.model.dao.mapper;

import com.pavlenko.kyrylo.model.dao.mapper.field.Fields;
import com.pavlenko.kyrylo.model.entity.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandMapper {

    public Brand extractFromResultSet(ResultSet rs) throws SQLException {
        return new Brand(
                rs.getLong(Fields.BRAND_ID),
                Brand.BrandEnum.valueOf(rs.getString(Fields.BRAND))
        );
    }
}

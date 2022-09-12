package com.pavlenko.kyrylo.model.dao.mapper;

import com.pavlenko.kyrylo.model.dao.mapper.field.Fields;
import com.pavlenko.kyrylo.model.entity.Car;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CarMapper {

    private final BrandMapper brandMapper = new BrandMapper();
    private final QualityMapper qualityMapper = new QualityMapper();
    private final CarStatusMapper carStatusMapper = new CarStatusMapper();

    public Car extractFromResultSet(ResultSet rs) throws SQLException {
        return Car.builder()
                .id(rs.getLong(Fields.CAR_ID))
                .modelName(rs.getString(Fields.MODEL_NAME))
                .price(rs.getBigDecimal(Fields.PRICE))
                .brand(brandMapper.extractFromResultSet(rs))
                .qualityClass(qualityMapper.extractFromResultSet(rs))
                .status(carStatusMapper.extractFromResultSet(rs))
                .descriptionEn(rs.getString(Fields.DESCRIPTION_EN))
                .descriptionUa(rs.getString(Fields.DESCRIPTION_UA))
                .build();
    }

}

package com.pavlenko.kyrylo.model.dao.mapper;

import com.pavlenko.kyrylo.model.dao.mapper.field.Fields;
import com.pavlenko.kyrylo.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper {
    public Role extractFromResultSet(ResultSet rs) throws SQLException {
        return new Role(
                rs.getLong(Fields.ROLE_ID),
                Role.RoleEnum.valueOf(rs.getString(Fields.ROLE))
        );
    }
    public Role extractFromResultSet(ResultSet rs, String id) throws SQLException {
        return new Role(
                rs.getLong(id),
                Role.RoleEnum.valueOf(rs.getString(Fields.ROLE))
        );
    }
}

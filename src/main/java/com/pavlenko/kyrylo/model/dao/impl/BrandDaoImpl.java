package com.pavlenko.kyrylo.model.dao.impl;

import com.pavlenko.kyrylo.model.dao.BrandDao;
import com.pavlenko.kyrylo.model.dao.impl.query.BrandQueries;
import com.pavlenko.kyrylo.model.dao.impl.util.DBUtil;
import com.pavlenko.kyrylo.model.dao.mapper.BrandMapper;
import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandDaoImpl implements BrandDao {

    private final Logger logger = LogManager.getLogger(BrandDaoImpl.class);
    private final BrandMapper brandMapper = new BrandMapper();
    private final DataSource ds;
    private static final String ERROR_MASSAGE = "Error message: {}";

    public BrandDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Brand findBrandById(Long id) throws DataBaseException {
        ResultSet rs = null;
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(BrandQueries.FIND_BRAND_BY_ID)) {
            statement.setInt(1, Math.toIntExact(id));
            rs = statement.executeQuery();
            if (rs.next()) {
                return brandMapper.extractFromResultSet(rs);
            } else {
                throw new DataBaseException();
            }
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        } finally {
            DBUtil.closeResources(rs);
        }
    }

    @Override
    public List<Brand> findAllBrands() throws DataBaseException {
        try (Connection con = ds.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(BrandQueries.FIND_ALL_BRANDS)) {
            List<Brand> brandList = new ArrayList<>();
            while (rs.next()) {
                Brand brand = brandMapper.extractFromResultSet(rs);
                brandList.add(brand);
            }
            return brandList;
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }
}

package com.pavlenko.kyrylo.model.dao.impl;

import com.pavlenko.kyrylo.model.dao.QualityClassDao;
import com.pavlenko.kyrylo.model.dao.impl.query.QualityQueries;
import com.pavlenko.kyrylo.model.dao.impl.util.DBUtil;
import com.pavlenko.kyrylo.model.dao.mapper.QualityMapper;
import com.pavlenko.kyrylo.model.entity.Quality;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QualityClassDaoImpl implements QualityClassDao {

    private final Logger logger = LogManager.getLogger(QualityClassDaoImpl.class);
    private final QualityMapper qualityMapper = new QualityMapper();
    private final DataSource ds;
    private static final String ERROR_MASSAGE = "Error message: {}";

    public QualityClassDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Quality> findAllQualityClasses() throws DataBaseException {
        try (Connection con = ds.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(QualityQueries.FIND_ALL_QUALITY_CLASSES)) {

            List<Quality> qualityClassList = new ArrayList<>();
            while (rs.next()) {
                Quality quality = qualityMapper.extractFromResultSet(rs);
                qualityClassList.add(quality);
            }
            return qualityClassList;
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public Quality findQualityById(Long id) throws DataBaseException {
        ResultSet rs = null;
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(QualityQueries.FIND_QUALITY_CLASS_BY_ID)) {
            statement.setInt(1, Math.toIntExact(id));
            rs = statement.executeQuery();
            if (rs.next()) {
                return qualityMapper.extractFromResultSet(rs);
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

}

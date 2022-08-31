package com.pavlenko.kyrylo.model.dao.impl;

import com.pavlenko.kyrylo.model.dao.CarDao;
import com.pavlenko.kyrylo.model.dao.impl.query.CarQueries;
import com.pavlenko.kyrylo.model.dao.impl.query.CatalogQueryBuilder;
import com.pavlenko.kyrylo.model.dao.impl.util.DBUtil;
import com.pavlenko.kyrylo.model.dao.mapper.BrandMapper;
import com.pavlenko.kyrylo.model.dao.mapper.CarMapper;
import com.pavlenko.kyrylo.model.dao.mapper.CarStatusMapper;
import com.pavlenko.kyrylo.model.dao.mapper.QualityMapper;
import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.CarStatus;
import com.pavlenko.kyrylo.model.entity.Quality;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class CarDaoImpl implements CarDao {

    private final Logger logger = LogManager.getLogger(CarDaoImpl.class);
    private final CarMapper carMapper = new CarMapper();
    private final QualityMapper qualityMapper = new QualityMapper();
    private final CarStatusMapper carStatusMapper = new CarStatusMapper();
    private final BrandMapper brandMapper = new BrandMapper();
    private final DataSource ds;
    private static final String ERROR_MASSAGE = "Error message: {}";
    private static final String EDIT_ERROR_MESSAGE = "Error occurred while trying to edit car with id ({}), {}";

    public CarDaoImpl(DataSource ds) {
        this.ds = ds;
    }


    @Override
    public void create(Car entity) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(CarQueries.CREATE_CAR)) {
            statement.setString(1, entity.getModelName());
            statement.setBigDecimal(2, entity.getPrice());
            statement.setString(3, String.valueOf(entity.getBrand().getValue()));
            statement.setString(4, entity.getQualityClass().getValue().name());
            statement.setString(5, entity.getDescription());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public Car findById(Long id) throws DataBaseException {
        ResultSet rs = null;
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(CarQueries.FIND_CAR_BY_ID)) {
            statement.setLong(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                return carMapper.extractFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        } finally {
            DBUtil.closeResources(rs);
        }
    }

    @Override
    public List<Car> findAll() throws DataBaseException {
        try (Connection con = ds.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(CarQueries.FIND_ALL_FROM_CARS)){
            List<Car> carsList = new ArrayList<>();
            while (rs.next()) {
                Car car = carMapper.extractFromResultSet(rs);
                carsList.add(car);
            }
            return carsList;
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public List<Car> findAllCarsWithFilters(Map<String, String> filterParam, boolean adminRequest) throws DataBaseException {
        String queryWithFilters = CatalogQueryBuilder.buildCarQueryFilterForFindAll(filterParam, adminRequest);
        try (Connection con = ds.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(queryWithFilters)) {
            List<Car> carsList = new ArrayList<>();
            while (rs.next()) {
                Car car = carMapper.extractFromResultSet(rs);
                carsList.add(car);
            }
            return carsList;
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }


    public List<Quality> findAllQualityClasses() throws DataBaseException {
        try (Connection con = ds.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(CarQueries.FIND_ALL_QUALITY_CLASSES)) {

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

    public List<CarStatus> findAllStatuses() throws DataBaseException {
        try (Connection con = ds.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(CarQueries.FIND_ALL_CAR_STATUSES)) {
            List<CarStatus> statusList = new ArrayList<>();
            while (rs.next()) {
                CarStatus status = carStatusMapper.extractFromResultSet(rs);
                statusList.add(status);
            }
            return statusList;
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public List<Brand> findAllBrands() throws DataBaseException {
        try (Connection con = ds.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(CarQueries.FIND_ALL_BRANDS)) {
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




    @Override
    public Brand findBrandById(Long id) throws DataBaseException {
        ResultSet rs = null;
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(CarQueries.FIND_BRAND_BY_ID)) {
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
    public Quality findQualityById(Long id) throws DataBaseException {
        ResultSet rs = null;
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(CarQueries.FIND_QUALITY_CLASS_BY_ID)) {
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

    @Override
    public void editCarPrice(BigDecimal input, Long id) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(CarQueries.UPDATE_CAR_PRICE)) {
            statement.setBigDecimal(1, input);
            statement.setInt(2, Math.toIntExact(id));
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(EDIT_ERROR_MESSAGE, id, e.getMessage());
            throw new DataBaseException();
        }
    }


    @Override
    public void editCarStatus(Long id, Long inputId) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(CarQueries.UPDATE_CAR_STATUS)) {
            statement.setInt(1, Math.toIntExact(inputId));
            statement.setInt(2, Math.toIntExact(id));
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(EDIT_ERROR_MESSAGE, id, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public void editCarQuality(Long id, Long inputId) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(CarQueries.UPDATE_CAR_QUALITY)) {
            statement.setInt(1, Math.toIntExact(inputId));
            statement.setInt(2, Math.toIntExact(id));
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(EDIT_ERROR_MESSAGE, id, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public void editCarDescription(Long id, String input) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(CarQueries.UPDATE_CAR_DESCRIPTION)) {
            statement.setString(1, input);
            statement.setInt(2, Math.toIntExact(id));
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(EDIT_ERROR_MESSAGE, id, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public void delete(long id) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(CarQueries.DELETE_CAR_BY_ID)) {
            statement.setInt(1, Math.toIntExact(id));
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public int checkCarStatusId(Long carId) throws DataBaseException {
        ResultSet rs = null;
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(CarQueries.SELECT_CAR_STATUS)) {
            statement.setInt(1, Math.toIntExact(carId));
            rs = statement.executeQuery();
            int statusId = 0;
            if (rs.next()){
                statusId = rs.getInt("car_status_id");
            }
            return statusId;
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        } finally {
            DBUtil.closeResources(rs);
        }
    }


}

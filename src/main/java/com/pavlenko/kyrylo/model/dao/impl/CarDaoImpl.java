package com.pavlenko.kyrylo.model.dao.impl;

import com.pavlenko.kyrylo.model.dao.CarDao;
import com.pavlenko.kyrylo.model.dao.impl.query.CarQueries;
import com.pavlenko.kyrylo.model.dao.impl.query.CatalogQueryBuilder;
import com.pavlenko.kyrylo.model.dao.impl.util.DBUtil;
import com.pavlenko.kyrylo.model.dao.mapper.CarMapper;
import com.pavlenko.kyrylo.model.dao.mapper.CarStatusMapper;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.CarStatus;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.util.PaginationInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CarDaoImpl implements CarDao {

    private final Logger logger = LogManager.getLogger(CarDaoImpl.class);
    private final CarMapper carMapper = new CarMapper();

    private final CarStatusMapper carStatusMapper = new CarStatusMapper();
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
            statement.setString(5, entity.getDescriptionEn());
            statement.setString(6, entity.getDescriptionUa());

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
             ResultSet rs = statement.executeQuery(CarQueries.FIND_ALL_FROM_CARS)) {
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
    public PaginationInfo getPaginationResultData
            (Map<String, String> filterFieldMap, int limit, int offset, boolean adminRequest)
            throws DataBaseException {
        try {
            PaginationInfo paginationInfo = new PaginationInfo();

            List<Car> carList = findAllUsingFilterAndPagination(filterFieldMap, limit, offset, adminRequest);
            int carsQuantity = findFilteredCarsQuantity(filterFieldMap, adminRequest);

            paginationInfo.setCarListPage(carList);
            paginationInfo.setCarsCount(carsQuantity);

            return paginationInfo;
        } catch (DataBaseException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }

    }

    private List<Car> findAllUsingFilterAndPagination(
            Map<String, String> filterFieldMap, int limit, int offSet, boolean adminRequest)
            throws DataBaseException {

        String queryWithFilters = CatalogQueryBuilder
                .buildCarQueryFilterForFindAll(filterFieldMap, adminRequest);
        String queryWithFiltersAndPagination = queryWithFilters + CarQueries.LIMIT_OFFSET;
        ResultSet rs = null;
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(queryWithFiltersAndPagination)) {
            List<Car> carList = new ArrayList<>();
            statement.setInt(1, limit);
            statement.setInt(2, offSet);
            rs = statement.executeQuery();
            while (rs.next()) {
                Car car = carMapper.extractFromResultSet(rs);
                carList.add(car);
            }
            return carList;
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        } finally {
            DBUtil.closeResources(rs);
        }
    }

    private int findFilteredCarsQuantity(Map<String, String> filterFieldMap, boolean adminRequest)
            throws DataBaseException {
        String findAllWithFilterCount = CatalogQueryBuilder.buildCarQueryFilterForFindAllCount
                (filterFieldMap, adminRequest);
        try (Connection con = ds.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(findAllWithFilterCount)) {
            if (resultSet.next()) {
                return resultSet.getInt("count(*)");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            logger.error("{}, when trying to find cars quantity", e.getMessage());
            throw new DataBaseException();
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
    public void editCarDescriptionEn(Long id, String input) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(CarQueries.UPDATE_CAR_DESCRIPTION_EN)) {
            statement.setString(1, input);
            statement.setInt(2, Math.toIntExact(id));
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(EDIT_ERROR_MESSAGE, id, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public void editCarDescriptionUa(Long id, String input) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(CarQueries.UPDATE_CAR_DESCRIPTION_UA)) {
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
    public int checkStatus(Long carId) throws DataBaseException {
        ResultSet rs = null;
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(CarQueries.SELECT_CAR_STATUS)) {
            statement.setInt(1, Math.toIntExact(carId));
            rs = statement.executeQuery();
            int statusId = 0;
            if (rs.next()) {
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

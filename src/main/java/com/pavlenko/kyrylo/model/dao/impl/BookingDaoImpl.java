package com.pavlenko.kyrylo.model.dao.impl;

import com.pavlenko.kyrylo.model.dao.BookingDao;
import com.pavlenko.kyrylo.model.dao.impl.query.BookingQueries;
import com.pavlenko.kyrylo.model.dao.impl.query.CarQueries;
import com.pavlenko.kyrylo.model.dao.mapper.BookingMapper;
import com.pavlenko.kyrylo.model.entity.Booking;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookingDaoImpl implements BookingDao {

    private final Logger logger = LogManager.getLogger(BookingDaoImpl.class);
    private final DataSource ds;
    private final BookingMapper bookingMapper = new BookingMapper();

    private static final String ERROR_MASSAGE = "Error message: {}";
    private static final String ROLLBACK_FAILED_MASSAGE = "Rollback failed, {}";
    private static final String ROLLBACK_MASSAGE = "Rollback {}";


    public BookingDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public void create(Booking entity) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(BookingQueries.CREATE_BOOKING)) {
            createBookingShortcut(entity, statement);
            logger.info("Created new booking...");
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public Booking findById(Long id) throws DataBaseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Booking> findAll() throws DataBaseException {
        try (Connection con = ds.getConnection();
             Statement statement = con.createStatement()) {
            List<Booking> bookingList = new ArrayList<>();
            ResultSet rs = statement.executeQuery(BookingQueries.FIND_ALL_PENDING_REVIEW);
            while (rs.next()) {
                Booking booking = bookingMapper.extractFromResultSet(rs);
                bookingList.add(booking);
            }
            return bookingList;
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public void delete(long id) throws DataBaseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void registerNewBooking(Booking entity, Long carId, Long carStatusId)
            throws DataBaseException, SQLException {
        Connection con = null;
        PreparedStatement createStatement = null;
        PreparedStatement editCarStatusStatement = null;
        try {
            con = ds.getConnection();
            createStatement = con.prepareStatement(BookingQueries.CREATE_BOOKING);
            editCarStatusStatement = con.prepareStatement(CarQueries.UPDATE_CAR_STATUS);

            con.setAutoCommit(false);
            createBookingShortcut(entity, createStatement);

            editCarStatusStatement.setInt(1, Math.toIntExact(carStatusId));
            editCarStatusStatement.setInt(2, Math.toIntExact(carId));
            editCarStatusStatement.executeUpdate();

            con.commit();
            logger.info("Created new booking...");
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                logger.error(ROLLBACK_FAILED_MASSAGE, ex.getMessage());
                throw new DataBaseException();
            }
            logger.error(ROLLBACK_MASSAGE, e.getMessage());
            throw new DataBaseException();
        } finally {
            Objects.requireNonNull(con).close();
            Objects.requireNonNull(createStatement).close();
            Objects.requireNonNull(editCarStatusStatement).close();
        }

    }

    private void createBookingShortcut(Booking entity, PreparedStatement createStatement) throws SQLException {
        createStatement.setInt(1, Math.toIntExact(entity.getUser().getId()));
        createStatement.setInt(2, Math.toIntExact(entity.getCar().getCarId()));
        createStatement.setString(3, entity.getUserDetails());
        if (entity.isWithDriver()) {
            createStatement.setInt(4, 0);
        } else {
            createStatement.setInt(4, 1);
        }
        createStatement.setDate(5, Date.valueOf(entity.getStartDate()));
        createStatement.setDate(6, Date.valueOf(entity.getEndDate()));
        createStatement.setBigDecimal(7, entity.getPrice());
        createStatement.executeUpdate();
    }

    @Override
    public List<Booking> findByUserId(Long id) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(BookingQueries.FIND_BY_USER_ID)) {
            statement.setInt(1, Math.toIntExact(id));
            ResultSet rs = statement.executeQuery();
            List<Booking> bookingList = new ArrayList<>();
            while (rs.next()) {
                Booking booking = bookingMapper.extractFromResultSet(rs);
                bookingList.add(booking);
            }
            return bookingList;
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public List<Booking> findAllRequestsByManagerId(Long id) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(BookingQueries.FIND_ALL_BY_MANAGER_ID)) {
            statement.setInt(1, Math.toIntExact(id));
            ResultSet rs = statement.executeQuery();
            List<Booking> bookingList = new ArrayList<>();
            while (rs.next()) {
                Booking booking = bookingMapper.extractFromResultSet(rs);
                bookingList.add(booking);
            }
            return bookingList;
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public void terminateRequestByID(Long id, Long carId, Long statusId) throws DataBaseException, SQLException {
        Connection con = null;
        PreparedStatement terminateStatement = null;
        PreparedStatement editCarStatusStatement = null;
        try {
            con = ds.getConnection();
            terminateStatement = con.prepareStatement(BookingQueries.TERMINATE_REQUEST_BY_ID);
            editCarStatusStatement = con.prepareStatement(CarQueries.UPDATE_CAR_STATUS);

            con.setAutoCommit(false);

            terminateStatement.setInt(1, Math.toIntExact(id));
            terminateStatement.executeUpdate();

            editCarStatusStatement.setInt(1, Math.toIntExact(statusId));
            editCarStatusStatement.setInt(2, Math.toIntExact(carId));
            editCarStatusStatement.executeUpdate();

            con.commit();
            logger.info("Request with id: {} was terminated by client", id);
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                logger.error(ROLLBACK_FAILED_MASSAGE, ex.getMessage());
                throw new DataBaseException();
            }
            logger.error(ROLLBACK_MASSAGE, e.getMessage());
            throw new DataBaseException();
        } finally {
            Objects.requireNonNull(con).close();
            Objects.requireNonNull(terminateStatement).close();
            Objects.requireNonNull(editCarStatusStatement).close();
        }
    }

    @Override
    public void takeOnReview(Long bookingId, Long managerId) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(BookingQueries.TAKE_ON_REVIEW)) {
            statement.setInt(1, Math.toIntExact(managerId));
            statement.setInt(2, Math.toIntExact(bookingId));

            statement.executeUpdate();
            logger.info("Request with id {} was taken by manager with id: {}", bookingId, managerId);
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public void updateBookingStatusId(Long bookingId, Long statusId) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(BookingQueries.UPDATE_BOOKING_STATUS)) {
            statement.setInt(1, Math.toIntExact(statusId));
            statement.setInt(2, Math.toIntExact(bookingId));
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }

    @Override
    public void registerReturn(Long bookingId, BigDecimal extraFee, Long carId, Long statusId)
            throws DataBaseException, SQLException {
        Connection con = null;
        PreparedStatement registerReturnStatement = null;
        PreparedStatement editCarStatusStatement = null;
        try {
            con = ds.getConnection();
            registerReturnStatement = con.prepareStatement(BookingQueries.REGISTER_RETURN);
            editCarStatusStatement = con.prepareStatement(CarQueries.UPDATE_CAR_STATUS);
            con.setAutoCommit(false);

            registerReturnStatement.setBigDecimal(1, extraFee);
            registerReturnStatement.setInt(2, Math.toIntExact(bookingId));
            registerReturnStatement.executeUpdate();

            editCarStatusStatement.setInt(1, Math.toIntExact(statusId));
            editCarStatusStatement.setInt(2, Math.toIntExact(carId));
            editCarStatusStatement.executeUpdate();

            con.commit();
            logger.info("Return of request with id: {}, was registered", bookingId);
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                logger.error(ROLLBACK_FAILED_MASSAGE, ex.getMessage());
                throw new DataBaseException();
            }
            logger.error(ROLLBACK_MASSAGE, e.getMessage());
            throw new DataBaseException();
        } finally {
            Objects.requireNonNull(con).close();
            Objects.requireNonNull(registerReturnStatement).close();
            Objects.requireNonNull(editCarStatusStatement).close();
        }
    }

    @Override
    public void addDecliningInfo(Long bookingId, String declineDescription, Long carId,
                                 Long bookingStatusId, Long carStatusId)
            throws DataBaseException, SQLException {
        Connection con = null;
        PreparedStatement addDeclineInfoStatement = null;
        PreparedStatement editBookingStatusStatement = null;
        PreparedStatement editCarStatusStatement = null;
        try {
            con = ds.getConnection();
            addDeclineInfoStatement = con.prepareStatement(BookingQueries.ADD_DECLINE_INFO);
            editBookingStatusStatement = con.prepareStatement(BookingQueries.UPDATE_BOOKING_STATUS);
            editCarStatusStatement = con.prepareStatement(CarQueries.UPDATE_CAR_STATUS);

            con.setAutoCommit(false);

            addDeclineInfoStatement.setString(1, declineDescription);
            addDeclineInfoStatement.setInt(2, Math.toIntExact(bookingId));
            addDeclineInfoStatement.executeUpdate();

            editBookingStatusStatement.setInt(1, Math.toIntExact(bookingStatusId));
            editBookingStatusStatement.setInt(2, Math.toIntExact(bookingId));
            editBookingStatusStatement.executeUpdate();

            editCarStatusStatement.setInt(1, Math.toIntExact(carStatusId));
            editCarStatusStatement.setInt(2, Math.toIntExact(carId));
            editCarStatusStatement.executeUpdate();

            con.commit();
            logger.info("Request with id: {}, was declined", bookingId);
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                logger.error(ROLLBACK_FAILED_MASSAGE, ex.getMessage());
                throw new DataBaseException();
            }
            logger.error(ROLLBACK_MASSAGE, e.getMessage());
            throw new DataBaseException();
        } finally {
            Objects.requireNonNull(con).close();
            Objects.requireNonNull(addDeclineInfoStatement).close();
            Objects.requireNonNull(editBookingStatusStatement).close();
            Objects.requireNonNull(editCarStatusStatement).close();
        }
    }

    @Override
    public boolean checkStatus(Long bookingId, Long statusId) throws DataBaseException {
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(BookingQueries.FIND_STATUS)) {
            statement.setInt(1, Math.toIntExact(bookingId));
            ResultSet rs = statement.executeQuery();
            int status = 0;
            if (rs.next()) {
                status = rs.getInt("status_id");
            }
            return status == statusId;
        } catch (SQLException e) {
            logger.error(ERROR_MASSAGE, e.getMessage());
            throw new DataBaseException();
        }
    }


}

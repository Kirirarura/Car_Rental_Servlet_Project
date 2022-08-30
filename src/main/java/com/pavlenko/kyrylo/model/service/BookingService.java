package com.pavlenko.kyrylo.model.service;

import com.pavlenko.kyrylo.model.dao.BookingDao;
import com.pavlenko.kyrylo.model.dto.BookingDto;
import com.pavlenko.kyrylo.model.entity.Booking;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Manages business logic related to the booking.
 */
public class BookingService {

    private final BookingDao bookingDao;

    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;

    }

    /**
     * Manages registering of new booking.
     *
     * @param bookingDto An Instance of BookingDto.
     */
    public void registerNewBooking(BookingDto bookingDto) throws DataBaseException {
        Booking booking = new Booking(bookingDto);
        bookingDao.registerNewBooking(booking, bookingDto.getCar().getCarId(), 2L);

    }

    /**
     * Returns list of bookings of one specific user.
     */
    public List<Booking> findByUserId(Long id) throws DataBaseException {
        return bookingDao.findByUserId(id);
    }

    public void terminateRequestById(Long bookingId, Long carId) throws DataBaseException {
        bookingDao.terminateRequestByID(bookingId, carId, 1L);
    }

    public List<Booking> findAllRequests() throws DataBaseException {
        return bookingDao.findAll();
    }

    /**
     * Returns list of bookings of one specific manager.
     */
    public List<Booking> findAllRequestsByManagerId(Long id) throws DataBaseException {
        return bookingDao.findAllRequestsByManagerId(id);
    }

    public void takeOnReview(Long requestId, Long managerId) throws DataBaseException {
        bookingDao.takeOnReview(requestId, managerId);
    }

    /**
     * Checks if request is already taken by another manager.
     */
    public boolean checkManagerOnRequest(Long requestId) throws DataBaseException {
        return bookingDao.checkStatus(requestId, 2L);
    }

    public void updateBookingStatusId(Long bookingId, Long statusId) throws DataBaseException {
        bookingDao.updateBookingStatusId(bookingId, statusId);
    }

    /**
     * Manages return registration.
     *
     * @param carId ID of car that is going to be available or send under repair after rent.
     * @param bookingId Booking ID that is going to finish
     * @param returnedInTime Boolean parameter that indicates if car returned in time.
     * @param damaged Boolean parameter that indicates if car returned damaged.
     * @throws DataBaseException Indicates that error occurred during database accessing.
     */
    public void registerReturn(Long carId, Long bookingId, boolean returnedInTime, boolean damaged)
            throws DataBaseException {
        long statusId = 1L;
        BigDecimal extraFee = calculateFee(returnedInTime, damaged);
        if (damaged) statusId = 3L;
        bookingDao.registerReturn(bookingId, extraFee, carId, statusId);
    }

    public boolean checkIfCanceled(Long bookingId) throws DataBaseException {
        return bookingDao.checkStatus(bookingId, 6L);
    }

    public void declineRequest(Long bookingId, String declineDescription, Long carId) throws DataBaseException {
        bookingDao.addDecliningInfo(bookingId, declineDescription, carId, 5L, 1L);
    }

    public void additionalPayment(Long bookingId) throws DataBaseException {
        bookingDao.additionalPayment(bookingId);
    }

    /**
     * Calculates additional fee according to the manager inputs.
     *
     * @param returnedInTime Boolean parameter that indicates if car returned in time.
     * @param damaged Boolean parameter that indicates if car returned damaged.
     */
    public BigDecimal calculateFee(boolean returnedInTime, boolean damaged) {
        BigDecimal extraFee = new BigDecimal(0);
        if (!returnedInTime) {
            extraFee = extraFee.add(new BigDecimal(20));
        }
        if (damaged) {
            extraFee = extraFee.add(new BigDecimal(50));
        }
        return extraFee;
    }
}

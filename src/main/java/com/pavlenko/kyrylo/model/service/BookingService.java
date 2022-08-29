package com.pavlenko.kyrylo.model.service;

import com.pavlenko.kyrylo.model.dao.BookingDao;
import com.pavlenko.kyrylo.model.dto.BookingDto;
import com.pavlenko.kyrylo.model.entity.Booking;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;

import java.math.BigDecimal;
import java.util.List;

public class BookingService {

    private final BookingDao bookingDao;

    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;

    }

    public void registerNewBooking(BookingDto bookingDto) throws DataBaseException {
        Booking booking = new Booking(bookingDto);
        bookingDao.registerNewBooking(booking, bookingDto.getCar().getCarId(), 2L);

    }

    public List<Booking> findByUserId(Long id) throws DataBaseException {
        return bookingDao.findByUserId(id);
    }

    public void terminateRequestById(Long bookingId, Long carId) throws DataBaseException {
        bookingDao.terminateRequestByID(bookingId, carId, 1L);
    }

    public List<Booking> findAllRequests() throws DataBaseException {
        return bookingDao.findAll();
    }

    public List<Booking> findAllRequestsByManagerId(Long id) throws DataBaseException {
        return bookingDao.findAllRequestsByManagerId(id);
    }

    public void takeOnReview(Long requestId, Long managerId) throws DataBaseException {
        bookingDao.takeOnReview(requestId, managerId);
    }

    public boolean checkManagerOnRequest(Long requestId) throws DataBaseException {
        return bookingDao.checkStatus(requestId, 2L);
    }

    public void updateBookingStatusId(Long bookingId, Long statusId) throws DataBaseException {
        bookingDao.updateBookingStatusId(bookingId, statusId);
    }

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

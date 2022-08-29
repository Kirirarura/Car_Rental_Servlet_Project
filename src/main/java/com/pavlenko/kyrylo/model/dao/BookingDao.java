package com.pavlenko.kyrylo.model.dao;

import com.pavlenko.kyrylo.model.entity.Booking;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;

import java.math.BigDecimal;
import java.util.List;

public interface BookingDao extends GenericDao<Booking>{

    void registerNewBooking(Booking booking, Long carId, Long carStatusId) throws DataBaseException;
    List<Booking> findByUserId(Long id) throws DataBaseException;
    List<Booking> findAllRequestsByManagerId(Long id) throws DataBaseException;
    void terminateRequestByID(Long id, Long carId, Long statusId) throws DataBaseException;
    void takeOnReview(Long bookingId, Long managerId) throws DataBaseException;
    void updateBookingStatusId(Long bookingId, Long statusId) throws DataBaseException;
    void addDecliningInfo(Long bookingId, String declineDescription, Long carId,
                          Long bookingStatusId, Long carStatusId) throws DataBaseException;
    void registerReturn(Long bookingId, BigDecimal extraFee, Long carId, Long statusId) throws DataBaseException;
    boolean checkStatus(Long bookingId, Long statusId) throws DataBaseException;
    void additionalPayment(Long bookingId) throws DataBaseException;


}

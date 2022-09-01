package model.service;

import com.pavlenko.kyrylo.model.dao.BookingDao;
import com.pavlenko.kyrylo.model.dto.BookingDto;
import com.pavlenko.kyrylo.model.entity.Booking;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.User;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.BookingService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    BookingDao bookingDao = mock(BookingDao.class);
    BookingService bookingService = new BookingService(bookingDao);

    private static final User USER = new User();
    private static final Car CAR = new Car();
    private static final String USER_DETAILS = "123456789";
    private static final boolean WITH_DRIVER = true;
    private static final String START_DATE = String.valueOf(LocalDate.now());
    private static final String END_DATE = String.valueOf(LocalDate.now().plusDays(3));
    private static final String PRICE = "50";

    private static final BookingDto BOOKING_DTO = new BookingDto(
            USER,
            CAR,
            USER_DETAILS,
            WITH_DRIVER,
            START_DATE,
            END_DATE,
            PRICE
    );


    @Test
    void testRegisterNewBooking() throws DataBaseException {
        bookingService.registerNewBooking(BOOKING_DTO);
        Booking booking = new Booking(BOOKING_DTO);
        verify(bookingDao, times(1)).registerNewBooking(booking, null, 2L);
    }

    @Test
    void testFindByUsrId() throws DataBaseException {
        bookingService.findByUserId(1L);
        verify(bookingDao, times(1)).findByUserId(1L);
    }

    @Test
    void testTerminateRequestById() throws DataBaseException {
        bookingService.terminateRequestById(1L, 1L);
        verify(bookingDao, times(1)).terminateRequestByID(1L, 1L, 1L);
    }

    @Test
    void testFindAllRequests() throws DataBaseException {
        bookingService.findAllRequests();
        verify(bookingDao, times(1)).findAll();
    }

    @Test
    void testFindAllRequestsByManagerId() throws DataBaseException {
        bookingService.findAllRequestsByManagerId(1L);
        verify(bookingDao, times(1)).findAllRequestsByManagerId(1L);
    }

    @Test
    void testTakeOnReview() throws DataBaseException {
        bookingService.takeOnReview(1L, 1L);
        verify(bookingDao, times(1)).takeOnReview(1L, 1L);
    }

    @Test
    void testCheckManagerOnRequest() throws DataBaseException {
        bookingService.checkManagerOnRequest(1L);
        verify(bookingDao, times(1)).checkStatus(1L, 2L);
    }

    @Test
    void testUpdateBookingStatusId() throws DataBaseException {
        bookingService.updateBookingStatusId(1L, 1L);
        verify(bookingDao, times(1)).updateBookingStatusId(1L, 1L);
    }

    @Test
    void testRegisterReturn() throws DataBaseException {
        bookingService.registerReturn(1L, 1L, true, false);
        verify(bookingDao, times(1))
                .registerReturn(1L, new BigDecimal(0), 1L, 1L);
    }

    @Test
    void testCalculateFeeZero() {
        BigDecimal extraFee = bookingService.calculateFee(true, false);
        assertEquals(new BigDecimal(0), extraFee);
    }
    @Test
    void testCalculateFeeReturnedNotInTime() {
        BigDecimal extraFee = bookingService.calculateFee(false, false);
        assertEquals(new BigDecimal(20), extraFee);
    }
    @Test
    void testCalculateFeeDamaged() {
        BigDecimal extraFee = bookingService.calculateFee(true, true);
        assertEquals(new BigDecimal(50), extraFee);
    }
    @Test
    void testCalculateFeeReturnedNotInTimeAndDamaged() {
        BigDecimal extraFee = bookingService.calculateFee(false, true);
        assertEquals(new BigDecimal(70), extraFee);
    }

    @Test
    void testCheckIfCanceled() throws DataBaseException {
        bookingService.checkIfCanceled(1L);
        verify(bookingDao, times(1)).checkStatus(1L, 6L);
    }

    @Test
    void testDeclineRequest() throws DataBaseException {
        bookingService.declineRequest(1L, "description", 1L);
        verify(bookingDao, times(1))
                .addDecliningInfo(1L,"description", 1L, 5L, 1L);
    }

    @Test
    void testAdditionalPayment() throws DataBaseException {
        bookingService.additionalPayment(1L);
        verify(bookingDao, times(1)).additionalPayment(1L);
    }



}

package model.entity;


import com.pavlenko.kyrylo.model.dto.BookingDto;
import com.pavlenko.kyrylo.model.entity.Booking;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class BookingPriceTest {

    private static final LocalDate START_DATE = LocalDate.now();
    private static final LocalDate END_DATE = START_DATE.plusDays(3);
    private static final String USER_DETAILS = "123456789";
    private static final BigDecimal PRICE = new BigDecimal(50);

    private BookingDto BOOKING_DTO;

    @BeforeEach
    void init() {
        BOOKING_DTO = new BookingDto(
                new User(),
                new Car(),
                USER_DETAILS,
                true,
                START_DATE.toString(),
                END_DATE.toString(),
                PRICE
        );
    }

    @Test
    void testCorrectCalculatePrice() {
        Booking booking = new Booking(BOOKING_DTO);
        Assertions.assertEquals(new BigDecimal(180), booking.getPrice());
    }

    @Test
    void testIncorrectCalculatePrice() {
        Booking booking = new Booking(BOOKING_DTO);
        Assertions.assertNotEquals(new BigDecimal(200), booking.getPrice());
    }

}

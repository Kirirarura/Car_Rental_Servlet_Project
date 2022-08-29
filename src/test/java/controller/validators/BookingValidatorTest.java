package controller.validators;

import com.pavlenko.kyrylo.controller.validator.BookingValidator;
import com.pavlenko.kyrylo.controller.validator.UserValidator;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.dto.BookingDto;
import com.pavlenko.kyrylo.model.dto.UserDto;
import com.pavlenko.kyrylo.model.entity.Car;
import com.pavlenko.kyrylo.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BookingValidatorTest {

    private final HttpServletRequest REQUEST = mock(HttpServletRequest.class);


    private static final User CORRECT_USER = new User();
    private static final Car CORRECT_CAR = new Car();
    private static final String CORRECT_USER_DETAILS = "123456789";
    private static final boolean CORRECT_WITH_DRIVER = true;
    private static final String CORRECT_START_DATE = String.valueOf(LocalDate.now());
    private static final String CORRECT_END_DATE = String.valueOf(LocalDate.now().plusDays(3));
    private static final String CORRECT_PRICE = "50";


    private static final String INCORRECT_INPUT_EMPTY = "";
    private static final String INCORRECT_USER_DETAILS = "12345";
    private static final String INCORRECT_START_DATE = String.valueOf(LocalDate.now().minusDays(1));
    private static final String INCORRECT_START_DATE_FORMAT = "08-3320-76";
    private static final String INCORRECT_END_DATE_FORMAT = "08-3320-76";
    private static final String INCORRECT_END_DATE_PERIOD = String.valueOf(LocalDate.now().minusDays(3));
    private BookingDto CORRECT_BOOKING_DTO;


    @BeforeEach
    void init(){
        CORRECT_BOOKING_DTO = new BookingDto(
                CORRECT_USER,
                CORRECT_CAR,
                CORRECT_USER_DETAILS,
                CORRECT_WITH_DRIVER,
                CORRECT_START_DATE,
                CORRECT_END_DATE,
                CORRECT_PRICE
        );
    }

    @Test
    void testValidateShouldReturnTrue() {
        assertTrue(BookingValidator.validate(CORRECT_BOOKING_DTO, REQUEST));
    }


    @Test
    void testValidateUserWithIncorrectUserDetails() {
        BookingDto BOOKING_WITH_INCORRECT_USERNAME = CORRECT_BOOKING_DTO;
        BOOKING_WITH_INCORRECT_USERNAME.setUserDetails(INCORRECT_USER_DETAILS);

        boolean isValid = BookingValidator.validate(BOOKING_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.PASSPORT_DATA_FORMAT_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectUserDetailsEmpty() {
        BookingDto BOOKING_WITH_INCORRECT_USERNAME = CORRECT_BOOKING_DTO;
        BOOKING_WITH_INCORRECT_USERNAME.setUserDetails(INCORRECT_INPUT_EMPTY);

        boolean isValid = BookingValidator.validate(BOOKING_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.EMPTY_FIELD_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectStartDate() {
        BookingDto BOOKING_WITH_INCORRECT_USERNAME = CORRECT_BOOKING_DTO;
        BOOKING_WITH_INCORRECT_USERNAME.setStartDate(INCORRECT_START_DATE);

        boolean isValid = BookingValidator.validate(BOOKING_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.DATES_PERIOD_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectStartDateEmpty() {
        BookingDto BOOKING_WITH_INCORRECT_USERNAME = CORRECT_BOOKING_DTO;
        BOOKING_WITH_INCORRECT_USERNAME.setStartDate(INCORRECT_INPUT_EMPTY);

        boolean isValid = BookingValidator.validate(BOOKING_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.EMPTY_FIELD_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectPeriodFormat() {
        BookingDto BOOKING_WITH_INCORRECT_USERNAME = CORRECT_BOOKING_DTO;
        BOOKING_WITH_INCORRECT_USERNAME.setStartDate(INCORRECT_START_DATE_FORMAT);
        BOOKING_WITH_INCORRECT_USERNAME.setEndDate(INCORRECT_END_DATE_FORMAT);

        boolean isValid = BookingValidator.validate(BOOKING_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.DATES_FORMAT_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectPeriodEmpty() {
        BookingDto BOOKING_WITH_INCORRECT_USERNAME = CORRECT_BOOKING_DTO;
        BOOKING_WITH_INCORRECT_USERNAME.setStartDate(INCORRECT_INPUT_EMPTY);
        BOOKING_WITH_INCORRECT_USERNAME.setEndDate(INCORRECT_INPUT_EMPTY);

        boolean isValid = BookingValidator.validate(BOOKING_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.EMPTY_FIELD_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectPeriod() {
        BookingDto BOOKING_WITH_INCORRECT_USERNAME = CORRECT_BOOKING_DTO;
        BOOKING_WITH_INCORRECT_USERNAME.setEndDate(INCORRECT_END_DATE_PERIOD);

        boolean isValid = BookingValidator.validate(BOOKING_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.DATES_PERIOD_EXCEPTION);
    }



}

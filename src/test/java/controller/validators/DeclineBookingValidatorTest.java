package controller.validators;


import com.pavlenko.kyrylo.controller.validator.DeclineBookingValidator;
import com.pavlenko.kyrylo.controller.validator.EditCarValidator;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DeclineBookingValidatorTest {

    private final HttpServletRequest REQUEST = mock(HttpServletRequest.class);

    private static final String CORRECT_DECLINE_INFO = "Some interesting info";
    private static final String EMPTY_DECLINE_INFO = "";
    private static final String INCORRECT_DECLINE_INFO = "no";

    @Test
    void testValidateShouldReturnTrue() {
        assertTrue(DeclineBookingValidator.validate(CORRECT_DECLINE_INFO, REQUEST));
    }


    @Test
    void testValidateDeclineBookingWithIncorrectInfo(){
        boolean isValid = DeclineBookingValidator.validate(INCORRECT_DECLINE_INFO, REQUEST);
        assertFalse(isValid);

        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.DESCRIPTION_SIZE_OUT_OF_BOUNDS_EXCEPTION);
    }
    @Test
    void testValidateDeclineBookingWithEmptyInfo(){
        boolean isValid = DeclineBookingValidator.validate(EMPTY_DECLINE_INFO, REQUEST);
        assertFalse(isValid);

        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.EMPTY_FIELD_EXCEPTION);
    }
}

package controller.validators;

import com.pavlenko.kyrylo.controller.validator.EditCarValidator;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class EditCarValidatorTest {

    private final HttpServletRequest REQUEST = mock(HttpServletRequest.class);


    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String CORRECT_PRICE_INPUT = "50";
    private static final String CORRECT_DESCRIPTION_INPUT = "Some interesting info";
    private static final String EMPTY_INPUT = "";

    private static final String INCORRECT_PRICE_INPUT = "incorrect data";
    private static final String INCORRECT_SIZE_PRICE_INPUT = "5";
    private static final String INCORRECT_NEGATIVE_PRICE_INPUT = "-50";
    private static final String INCORRECT_DESCRIPTION_INPUT = "no";


    @Test
    void testValidateShouldReturnTruePriceCase() {
        assertTrue(EditCarValidator.validate(PRICE, CORRECT_PRICE_INPUT, REQUEST));
    }

    @Test
    void testValidateShouldReturnTrueDescriptionCase() {
        assertTrue(EditCarValidator.validate(DESCRIPTION, CORRECT_DESCRIPTION_INPUT, REQUEST));
    }

    @Test
    void testValidateEditCarWithIncorrectPriceSize(){
        boolean isValid = EditCarValidator.validate(PRICE, INCORRECT_SIZE_PRICE_INPUT, REQUEST);
        assertFalse(isValid);

        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.PRICE_SIZE_OUT_OF_BOUNDS);
    }

    @Test
    void testValidateEditCarWithIncorrectNegativePrice(){
        boolean isValid = EditCarValidator.validate(PRICE, INCORRECT_NEGATIVE_PRICE_INPUT, REQUEST);
        assertFalse(isValid);

        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.PRICE_NEGATIVE_EXCEPTION);
    }

    @Test
    void testValidateEditCarWithIncorrectPriceInput(){
        boolean isValid = EditCarValidator.validate(PRICE, INCORRECT_PRICE_INPUT, REQUEST);
        assertFalse(isValid);

        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.WRONG_PRICE_INPUT_EXCEPTION);
    }

    @Test
    void testValidateEditCarWithIncorrectDescriptionInput(){
        boolean isValid = EditCarValidator.validate(DESCRIPTION, INCORRECT_DESCRIPTION_INPUT, REQUEST);
        assertFalse(isValid);

        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.DESCRIPTION_SIZE_OUT_OF_BOUNDS_EXCEPTION);
    }

    @Test
    void testValidateEditCarWithIncorrectDescriptionInputEmpty(){
        boolean isValid = EditCarValidator.validate(DESCRIPTION, EMPTY_INPUT, REQUEST);
        assertFalse(isValid);

        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.EMPTY_FIELD_EXCEPTION);
    }


}

package controller.validators;

import com.pavlenko.kyrylo.controller.validator.AddCarValidator;
import com.pavlenko.kyrylo.controller.validator.UserValidator;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.dto.CarDto;
import com.pavlenko.kyrylo.model.dto.UserDto;
import com.pavlenko.kyrylo.model.entity.Brand;
import com.pavlenko.kyrylo.model.entity.Quality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AddCarValidatorTest {

    private final HttpServletRequest REQUEST = mock(HttpServletRequest.class);

    private static final Brand CORRECT_BRAND = new Brand(Brand.BrandEnum.getRandomBrand());
    private static final String CORRECT_MODEL = "Some fancy model";
    private static final String CORRECT_PRICE = "50";
    private static final Quality CORRECT_QUALITY = new Quality(Quality.QualityEnum.getRandomQuality());
    private static final String CORRECT_DESCRIPTION = "Some interesting info";

    private static final String INCORRECT_MODEL = "The longest Model name in the world";
    private static final String INCORRECT_PRICE_INPUT = "OMEGALUL";
    private static final String INCORRECT_SIZE_PRICE = "5";
    private static final String INCORRECT_NEGATIVE_PRICE_INPUT = "-50";
    private static final String INCORRECT_DESCRIPTION = "no";
    private static final String INCORRECT_INPUT_EMPTY = "";




    private CarDto CORRECT_CAR_DTO;

    @BeforeEach
    void init() {
        CORRECT_CAR_DTO = new CarDto(
                CORRECT_BRAND,
                CORRECT_MODEL,
                CORRECT_PRICE,
                CORRECT_QUALITY,
                CORRECT_DESCRIPTION
        );
    }

    @Test
    void testValidateShouldReturnTrue() {
        assertTrue(AddCarValidator.validate(CORRECT_CAR_DTO, REQUEST));
    }


    @Test
    void testValidateUserWithIncorrectModel() {
        final CarDto CAR_WITH_INCORRECT_MODEL = CORRECT_CAR_DTO;
        CAR_WITH_INCORRECT_MODEL.setModel(INCORRECT_MODEL);

        boolean isValid = AddCarValidator.validate(CAR_WITH_INCORRECT_MODEL, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.MODEL_OUT_OF_BOUNDS_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectModelEmpty() {
        final CarDto CAR_WITH_INCORRECT_MODEL = CORRECT_CAR_DTO;
        CAR_WITH_INCORRECT_MODEL.setModel(INCORRECT_INPUT_EMPTY);

        boolean isValid = AddCarValidator.validate(CAR_WITH_INCORRECT_MODEL, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.EMPTY_FIELD_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectPriceInput() {
        final CarDto CAR_WITH_INCORRECT_MODEL = CORRECT_CAR_DTO;
        CAR_WITH_INCORRECT_MODEL.setPrice(INCORRECT_PRICE_INPUT);

        boolean isValid = AddCarValidator.validate(CAR_WITH_INCORRECT_MODEL, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.WRONG_PRICE_INPUT_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectPriceSize() {
        final CarDto CAR_WITH_INCORRECT_MODEL = CORRECT_CAR_DTO;
        CAR_WITH_INCORRECT_MODEL.setPrice(INCORRECT_SIZE_PRICE);

        boolean isValid = AddCarValidator.validate(CAR_WITH_INCORRECT_MODEL, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.PRICE_SIZE_OUT_OF_BOUNDS);
    }

    @Test
    void testValidateUserWithIncorrectPriceNegative() {
        final CarDto CAR_WITH_INCORRECT_MODEL = CORRECT_CAR_DTO;
        CAR_WITH_INCORRECT_MODEL.setPrice(INCORRECT_NEGATIVE_PRICE_INPUT);

        boolean isValid = AddCarValidator.validate(CAR_WITH_INCORRECT_MODEL, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.PRICE_NEGATIVE_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectPriceEmpty() {
        final CarDto CAR_WITH_INCORRECT_MODEL = CORRECT_CAR_DTO;
        CAR_WITH_INCORRECT_MODEL.setPrice(INCORRECT_INPUT_EMPTY);

        boolean isValid = AddCarValidator.validate(CAR_WITH_INCORRECT_MODEL, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.EMPTY_FIELD_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectDescription() {
        final CarDto CAR_WITH_INCORRECT_MODEL = CORRECT_CAR_DTO;
        CAR_WITH_INCORRECT_MODEL.setDescription(INCORRECT_DESCRIPTION);

        boolean isValid = AddCarValidator.validate(CAR_WITH_INCORRECT_MODEL, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.DESCRIPTION_SIZE_OUT_OF_BOUNDS_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectDescriptionEmpty() {
        final CarDto CAR_WITH_INCORRECT_MODEL = CORRECT_CAR_DTO;
        CAR_WITH_INCORRECT_MODEL.setDescription(INCORRECT_INPUT_EMPTY);

        boolean isValid = AddCarValidator.validate(CAR_WITH_INCORRECT_MODEL, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.EMPTY_FIELD_EXCEPTION);
    }


}

package com.pavlenko.kyrylo.controller.validator;

import com.pavlenko.kyrylo.controller.exeption.car.DescriptionSizeOutOfBoundsException;
import com.pavlenko.kyrylo.controller.exeption.car.PriceNegativeException;
import com.pavlenko.kyrylo.controller.exeption.car.PriceSizeOutOfBoundsException;
import com.pavlenko.kyrylo.controller.exeption.car.WrongInputException;
import com.pavlenko.kyrylo.controller.exeption.registration.EmptyFieldException;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Validates car editing info.
 */
public class EditCarValidator {

    private static final String STATUS = "status";
    private static final int PRICE_MAX_SIZE = 6;
    private static final int PRICE_MIN_SIZE = 2;
    private static final int DESCRIPTION_MAX_SIZE = 50;
    private static final int DESCRIPTION_MIN_SIZE = 4;
    private static final String PRICE = "price";
    private static final String DESCRIPTION_EN = "descriptionEn";
    private static final String DESCRIPTION_UA = "descriptionUa";


    private static final Logger logger = LogManager.getLogger(EditCarValidator.class);

    private EditCarValidator() {
    }

    /**
     * Validates car price input or description input, depending on the label.
     *
     * @param label Indicates what type of information is going to be edited.
     * @param input Data for editing that must be validated.
     * @return A boolean that indicates if input valid or not.
     */
    public static boolean validate(String label, String input, HttpServletRequest request) {
        try {
            checkPriceInput(label, input);
            checkPriceNegativeInput(label, input);
            checkPriceSize(label, input);
            checkDescriptionSizeEn(label, input);
            checkDescriptionSizeUa(label, input);
            return true;
        } catch (WrongInputException e) {
            logger.error("Wrong input");
            request.setAttribute(STATUS, StatusesContainer.WRONG_PRICE_INPUT_EXCEPTION);
        } catch (PriceSizeOutOfBoundsException e) {
            logger.error("Price size out of bounds");
            request.setAttribute(STATUS, StatusesContainer.PRICE_SIZE_OUT_OF_BOUNDS);
        } catch (EmptyFieldException e) {
            logger.error("Empty fields");
            request.setAttribute(STATUS, StatusesContainer.EMPTY_FIELD_EXCEPTION);
        } catch (DescriptionSizeOutOfBoundsException e) {
            logger.error("Description size out of bounds");
            request.setAttribute(STATUS, StatusesContainer.DESCRIPTION_SIZE_OUT_OF_BOUNDS_EXCEPTION);
        } catch (PriceNegativeException e) {
            logger.error("Price can not be negative");
            request.setAttribute(STATUS, StatusesContainer.PRICE_NEGATIVE_EXCEPTION);
        }
        return false;
    }

    private static void checkPriceInput(String label, String input) throws WrongInputException, EmptyFieldException {
        if (FieldValidator.fieldIsEmpty(input)) {
            throw new EmptyFieldException();
        }
        if (label.equals(PRICE)) {
            try {
                new BigDecimal(input);
            } catch (NumberFormatException nfe) {
                throw new WrongInputException();
            }
        }
    }

    private static void checkPriceNegativeInput(String label, String input) throws PriceNegativeException {
        if (label.equals(PRICE) && new BigDecimal(input).signum() <= 0) {
            throw new PriceNegativeException();
        }
    }

    private static void checkPriceSize(String label, String input) throws EmptyFieldException, PriceSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(input)) {
            throw new EmptyFieldException();
        }
        if (label.equals(PRICE) && (input.length() > PRICE_MAX_SIZE) || (input.length() < PRICE_MIN_SIZE)) {
            throw new PriceSizeOutOfBoundsException();
        }
    }

    private static void checkDescriptionSizeEn(String label, String input) throws EmptyFieldException, DescriptionSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(input)) {
            throw new EmptyFieldException();
        }
        if ((label.equals(DESCRIPTION_EN)) && ((input.length() > DESCRIPTION_MAX_SIZE) || (input.length() < DESCRIPTION_MIN_SIZE))) {
            throw new DescriptionSizeOutOfBoundsException();
        }
    }

    private static void checkDescriptionSizeUa(String label, String input) throws EmptyFieldException, DescriptionSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(input)) {
            throw new EmptyFieldException();
        }
        if ((label.equals(DESCRIPTION_UA)) && ((input.length() > DESCRIPTION_MAX_SIZE) || (input.length() < DESCRIPTION_MIN_SIZE))) {
            throw new DescriptionSizeOutOfBoundsException();
        }
    }

}

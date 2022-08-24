package com.pavlenko.kyrylo.controller.validator;

import com.pavlenko.kyrylo.controller.exeption.car.DescriptionSizeOutOfBoundsException;
import com.pavlenko.kyrylo.controller.exeption.car.PriceSizeOutOfBoundsException;
import com.pavlenko.kyrylo.controller.exeption.car.WrongInputException;
import com.pavlenko.kyrylo.controller.exeption.registration.EmptyFieldException;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EditCarValidator {

    private static final String STATUS = "status";
    private static final int PRICE_MAX_SIZE = 6;
    private static final int PRICE_MIN_SIZE = 2;
    private static final int DESCRIPTION_MAX_SIZE = 50;
    private static final int DESCRIPTION_MIN_SIZE = 4;

    private static final Logger logger = LogManager.getLogger(EditCarValidator.class);

    private EditCarValidator() {}
    public static boolean validate(String label, String input, HttpServletRequest request) {
        try {
            checkPriceInput(label, input);
            checkPriceSize(label, input);
            checkDescriptionSize(label, input);
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
        }
        return false;
    }
    private static void checkPriceInput(String label, String input) throws WrongInputException, EmptyFieldException {
        if (fieldIsEmpty(input)) {
            throw new EmptyFieldException();
        }
        if (label.equals("price")) {
            try {
                Double.parseDouble(input);
            } catch (NumberFormatException nfe) {
                throw new WrongInputException();
            }
        }
    }
    private static void checkPriceSize(String label, String input) throws EmptyFieldException, PriceSizeOutOfBoundsException {
        if (fieldIsEmpty(input)) {
            throw new EmptyFieldException();
        }
        if (label.equals("price")) {
            if ((input.length() > PRICE_MAX_SIZE) || (input.length() < PRICE_MIN_SIZE)){
                throw new PriceSizeOutOfBoundsException();
            }
        }
    }
    private static void checkDescriptionSize(String label, String input) throws EmptyFieldException, DescriptionSizeOutOfBoundsException {
        if (fieldIsEmpty(input)) {
            throw new EmptyFieldException();
        }
        if (label.equals("description")){
            if ((input.length() > DESCRIPTION_MAX_SIZE) || (input.length() < DESCRIPTION_MIN_SIZE)){
                throw new DescriptionSizeOutOfBoundsException();
            }
        }
    }
    private static boolean fieldIsEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }

}

package com.pavlenko.kyrylo.controller.validator;

import com.pavlenko.kyrylo.controller.exeption.car.*;
import com.pavlenko.kyrylo.controller.exeption.registration.EmptyFieldException;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.dto.CarDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Validates carDto
 */
public class AddCarValidator {

    private static final String STATUS = "status";
    private static final int PRICE_MAX_SIZE = 3;
    private static final int PRICE_MIN_SIZE = 2;
    private static final int DESCRIPTION_MAX_SIZE = 50;
    private static final int DESCRIPTION_MIN_SIZE = 4;
    private static final int MODEL_MAX_SIZE = 20;
    private static final Logger logger = LogManager.getLogger(AddCarValidator.class);

    private AddCarValidator() {
    }

    /**
     * Validates model, price, and description from carDto.
     *
     * @param carDto An instance of CarDto that must be validated.
     * @return A boolean that indicates if carDto valid or not.
     */
    public static boolean validate(CarDto carDto, HttpServletRequest request) {
        try {
            checkModelSize(carDto.getModel());
            checkPriceInput(carDto.getPrice());
            checkPriceNegativeInput(carDto.getPrice());
            checkPriceSize(carDto.getPrice());
            checkDescriptionSize(carDto.getDescriptionEn());
            checkDescriptionSize(carDto.getDescriptionUa());
            return true;
        } catch (WrongInputException e) {
            logger.warn("Wrong input");
            request.setAttribute(STATUS, StatusesContainer.WRONG_PRICE_INPUT_EXCEPTION);
        } catch (EmptyFieldException e) {
            logger.warn("Empty fields");
            request.setAttribute(STATUS, StatusesContainer.EMPTY_FIELD_EXCEPTION);
        } catch (PriceSizeOutOfBoundsException e) {
            logger.warn("Price size out of bounds");
            request.setAttribute(STATUS, StatusesContainer.PRICE_SIZE_OUT_OF_BOUNDS);
        } catch (ModelOutOfBoundsException e) {
            logger.warn("Model size out of bounds");
            request.setAttribute(STATUS, StatusesContainer.MODEL_OUT_OF_BOUNDS_EXCEPTION);
        } catch (DescriptionSizeOutOfBoundsException e) {
            logger.warn("Description size out of bounds");
            request.setAttribute(STATUS, StatusesContainer.DESCRIPTION_SIZE_OUT_OF_BOUNDS_EXCEPTION);
        } catch (PriceNegativeException e) {
            logger.warn("Price must be positive number");
            request.setAttribute(STATUS, StatusesContainer.PRICE_NEGATIVE_EXCEPTION);
        }
        return false;
    }

    private static void checkModelSize(String input) throws EmptyFieldException, ModelOutOfBoundsException {
        if (fieldIsEmpty(input)) {
            throw new EmptyFieldException();
        }

        if (input.length() > MODEL_MAX_SIZE) {
            throw new ModelOutOfBoundsException();
        }
    }

    private static void checkPriceInput(String input) throws WrongInputException, EmptyFieldException {
        if (fieldIsEmpty(input)) {
            throw new EmptyFieldException();
        }

        try {
            Double.parseDouble(input);
        } catch (NumberFormatException nfe) {
            throw new WrongInputException();
        }
    }

    private static void checkPriceNegativeInput(String input) throws PriceNegativeException {
        if (new BigDecimal(input).signum() <= 0){
            throw new PriceNegativeException();
        }
    }


    private static void checkPriceSize(String input) throws EmptyFieldException, PriceSizeOutOfBoundsException {
        if (fieldIsEmpty(input)) {
            throw new EmptyFieldException();
        }

        if ((input.length() > PRICE_MAX_SIZE) || (input.length() < PRICE_MIN_SIZE)) {
            throw new PriceSizeOutOfBoundsException();
        }
    }

    private static void checkDescriptionSize(String input) throws EmptyFieldException, DescriptionSizeOutOfBoundsException {
        if (fieldIsEmpty(input)) {
            throw new EmptyFieldException();
        }

        if ((input.length() > DESCRIPTION_MAX_SIZE) || (input.length() < DESCRIPTION_MIN_SIZE)) {
            throw new DescriptionSizeOutOfBoundsException();
        }

    }

    private static boolean fieldIsEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }
}

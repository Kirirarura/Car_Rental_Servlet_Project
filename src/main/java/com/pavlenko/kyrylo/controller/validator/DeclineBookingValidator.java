package com.pavlenko.kyrylo.controller.validator;

import com.pavlenko.kyrylo.controller.exeption.car.DescriptionSizeOutOfBoundsException;
import com.pavlenko.kyrylo.controller.exeption.registration.EmptyFieldException;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

public class DeclineBookingValidator {

    private static final int DECLINE_INFO_MAX_SIZE = 50;
    private static final int DECLINE_INFO_MIN_SIZE = 4;

    private static final Logger logger = LogManager.getLogger(DeclineBookingValidator.class);

    private DeclineBookingValidator() {}

    public static boolean validate(String input, HttpServletRequest request) {
        try {
            checkDeclineInfo(input);
            return true;
        } catch (EmptyFieldException e) {
            logger.warn("Empty fields");
            request.setAttribute(STATUS, StatusesContainer.EMPTY_FIELD_EXCEPTION);
        } catch (DescriptionSizeOutOfBoundsException e) {
            logger.warn("Description size is out of bounds");
            request.setAttribute(STATUS, StatusesContainer.DESCRIPTION_SIZE_OUT_OF_BOUNDS_EXCEPTION);
        }
        return false;
    }

    private static void checkDeclineInfo(String input) throws EmptyFieldException, DescriptionSizeOutOfBoundsException {
        if (fieldIsEmpty(input)) {
            throw new EmptyFieldException();
        }

        if ((input.length() > DECLINE_INFO_MAX_SIZE) || (input.length() < DECLINE_INFO_MIN_SIZE)) {
            throw new DescriptionSizeOutOfBoundsException();
        }
    }
    private static boolean fieldIsEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }

}

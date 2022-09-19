package com.pavlenko.kyrylo.controller.validator;

import com.pavlenko.kyrylo.controller.exeption.registration.*;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Validates userDto
 */
public class UserValidator {

    private static final String STATUS = "status";
    private static final int PASSWORD_MIN_SIZE = 8;
    private static final int PASSWORD_MAX_SIZE = 64;
    private static final int FIRST_NAME_MAX_SIZE = 32;
    private static final int LAST_NAME_MAX_SIZE = 32;
    private static final int EMAIL_MAX_SIZE = 128;
    public static final String EMAIL_PATTERN = "^[A-Za-z\\d._]+@[A-Za-z\\d.-]+\\.[A-Za-z]{2,6}$";

    private static final Logger logger = LogManager.getLogger(UserValidator.class);

    private UserValidator() {}

    /**
     * Validates password, firstName, lastName, email and password from userDto.
     *
     * @param userDto An instance of UserDto that must be validated.
     * @return A boolean that indicates if userDto is valid or not.
     */
    public static boolean validate(UserDto userDto, HttpServletRequest request) {
        try {
            checkPasswordSize(userDto.getPassword());
            checkPasswordMatch(userDto.getPassword(), userDto.getRepeatPassword());
            checkFirstNameSize(userDto.getFirstName());
            checkLastNameSize(userDto.getLastName());
            checkEmailSize(userDto.getEmail());
            checkEmailPattern(userDto.getEmail());
            return true;
        } catch (EmptyFieldException e) {
            logger.warn("Empty fields");
            request.setAttribute(STATUS, StatusesContainer.EMPTY_FIELD_EXCEPTION);
        } catch (PasswordSizeOutOfBoundsException e) {
            logger.warn("Password size is out of bounds");
            request.setAttribute(STATUS, StatusesContainer.PASSWORD_SIZE_OUT_OF_BOUNDS_EXCEPTION);
        } catch (PasswordMatchException e) {
            logger.warn("Repeat password doesn't match");
            request.setAttribute(STATUS, StatusesContainer.PASSWORD_MATCH_EXCEPTION);
        } catch (FirstNameSizeOutOfBoundsException e) {
            logger.warn("Firstname size is out of bounds");
            request.setAttribute(STATUS, StatusesContainer.FIRST_NAME_SIZE_OUT_OF_BOUNDS_EXCEPTION);
        } catch (LastNameSizeOutOfBoundsException e) {
            logger.warn("Lastname size is out of bounds");
            request.setAttribute(STATUS, StatusesContainer.LAST_NAME_SIZE_OUT_OF_BOUNDS_EXCEPTION);
        } catch (EmailSizeOutOfBoundsException e) {
            logger.warn("Email size is out of bounds");
            request.setAttribute(STATUS, StatusesContainer.EMAIL_SIZE_OUT_OF_BOUNDS_EXCEPTION);
        } catch (EmailNotMatchPatternException e) {
            logger.warn("Email not match pattern");
            request.setAttribute(STATUS, StatusesContainer.EMAIL_NOT_MATCH_PATTERN_EXCEPTION);
        }
        return false;
    }

    private static void checkPasswordSize(String password) throws PasswordSizeOutOfBoundsException, EmptyFieldException {
        if (FieldValidator.fieldIsEmpty(password)) {
            throw new EmptyFieldException();
        }

        int size = password.length();

        if (size < PASSWORD_MIN_SIZE || size > PASSWORD_MAX_SIZE) {
            throw new PasswordSizeOutOfBoundsException();
        }
    }

    private static void checkPasswordMatch(String password, String repeatPassword) throws PasswordMatchException, EmptyFieldException {
        if (FieldValidator.fieldIsEmpty(password) || FieldValidator.fieldIsEmpty(repeatPassword)) {
            throw new EmptyFieldException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordMatchException();
        }
    }

    private static void checkFirstNameSize(String firstName) throws EmptyFieldException, FirstNameSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(firstName)) {
            throw new EmptyFieldException();
        }
        if (firstName.length() > FIRST_NAME_MAX_SIZE) {
            throw new FirstNameSizeOutOfBoundsException();
        }
    }

    private static void checkLastNameSize(String lastName) throws EmptyFieldException, LastNameSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(lastName)) {
            throw new EmptyFieldException();
        }
        if (lastName.length() > LAST_NAME_MAX_SIZE) {
            throw new LastNameSizeOutOfBoundsException();
        }
    }

    private static void checkEmailSize(String email) throws EmptyFieldException, EmailSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(email)) {
            throw new EmptyFieldException();
        }
        if (email.length() > EMAIL_MAX_SIZE) {
            throw new EmailSizeOutOfBoundsException();
        }
    }

    private static void checkEmailPattern(String email) throws EmailNotMatchPatternException {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new EmailNotMatchPatternException();
        }
    }
}

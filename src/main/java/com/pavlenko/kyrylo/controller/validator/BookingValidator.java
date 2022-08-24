package com.pavlenko.kyrylo.controller.validator;

import com.pavlenko.kyrylo.controller.exeption.booking.DatesFormatException;
import com.pavlenko.kyrylo.controller.exeption.booking.DatesPeriodException;
import com.pavlenko.kyrylo.controller.exeption.booking.PassportDataFormatException;
import com.pavlenko.kyrylo.controller.exeption.registration.EmptyFieldException;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.dto.BookingDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookingValidator {

    private static final String STATUS = "status";
    private static final String PASSPORT_REGEX = "\\d{9}";
    private static final Logger logger = LogManager.getLogger(BookingValidator.class);

    private BookingValidator() {}

    public static boolean validate(BookingDto bookingDto, HttpServletRequest request) {
        try {
            checkUserDetails(bookingDto.getUserDetails());
            checkRentPeriodFormat(bookingDto.getStartDate(), bookingDto.getEndDate());
            checkFirstDate(bookingDto.getStartDate());
            checkRentPeriod(bookingDto.getStartDate(), bookingDto.getEndDate());
            return true;
        } catch (EmptyFieldException e) {
            logger.warn("Empty fields");
            request.setAttribute(STATUS, StatusesContainer.EMPTY_FIELD_EXCEPTION);
        } catch (PassportDataFormatException e) {
            logger.warn("Passport format exception");
            request.setAttribute(STATUS, StatusesContainer.PASSPORT_DATA_FORMAT_EXCEPTION);
        } catch (DatesFormatException e) {
            logger.warn("Dates format exception");
            request.setAttribute(STATUS, StatusesContainer.DATES_FORMAT_EXCEPTION);
        } catch (DatesPeriodException e) {
            logger.warn("Dates period exception");
            request.setAttribute(STATUS, StatusesContainer.DATES_PERIOD_EXCEPTION);
        }
        return false;
    }

    private static void checkUserDetails(String input) throws EmptyFieldException, PassportDataFormatException {
        if (fieldIsEmpty(input)){
            throw new EmptyFieldException();
        }
        Pattern pattern = Pattern.compile(PASSPORT_REGEX);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()){
            throw new PassportDataFormatException();
        }

    }

    private static void checkFirstDate(String startDate) throws DatesPeriodException {
        LocalDate today = LocalDate.now();
        LocalDate date = LocalDate.parse(startDate);
        if (date.isBefore(today)){
            throw new DatesPeriodException();
        }
    }

    private static void checkRentPeriodFormat(String startDate, String endDate) throws EmptyFieldException, DatesFormatException {
        if (fieldIsEmpty(startDate) || fieldIsEmpty(endDate)){
            throw new EmptyFieldException();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        try {
            LocalDate.parse(startDate, formatter);
            LocalDate.parse(endDate, formatter);
        } catch (DateTimeParseException e) {
            throw new DatesFormatException();
        }
    }

    private static void checkRentPeriod(String startDate, String endDate) throws DatesPeriodException {
        Period period = Period.between(LocalDate.parse(startDate), LocalDate.parse(endDate));
        if (period.getDays() <= 0) {
            throw new DatesPeriodException();
        }
    }

    private static boolean fieldIsEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }

}

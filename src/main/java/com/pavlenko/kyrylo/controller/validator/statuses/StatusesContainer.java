package com.pavlenko.kyrylo.controller.validator.statuses;

public final class StatusesContainer {
    private StatusesContainer() {}

    public static final String EMPTY_FIELD_EXCEPTION = "emptyFieldException";

    // -- Registration -- //
    public static final String PASSWORD_SIZE_OUT_OF_BOUNDS_EXCEPTION = "passwordSizeOutOfBoundsException";
    public static final String PASSWORD_MATCH_EXCEPTION = "passwordMatchException";
    public static final String FIRST_NAME_SIZE_OUT_OF_BOUNDS_EXCEPTION = "firstnameSizeOutOfBoundsException";
    public static final String LAST_NAME_SIZE_OUT_OF_BOUNDS_EXCEPTION = "lastnameSizeOutOfBoundsException";
    public static final String EMAIL_SIZE_OUT_OF_BOUNDS_EXCEPTION = "emailSizeOutOfBoundsException";
    public static final String EMAIL_NOT_MATCH_PATTERN_EXCEPTION = "emailMatchPatternException";
    public static final String EMAIL_IS_RESERVED_EXCEPTION = "emailIsReserved";
    public static final String REGISTRATION_EXCEPTION = "registrationException";

    // -- Login -- //
    public static final String USER_BLOCKED_EXCEPTION = "userBlockedException";
    public static final String FAILED_LOGIN_EXCEPTION = "failedLoginException";
    public static final String AUTHENTICATION_EXCEPTION = "authenticationException";
    public static final String NOT_VERIFIED_ACCOUNT_EXCEPTION = "notVerifiedAccountException";

    // -- Info loading -- //
    public static final String CAR_INFO_LOADING_EXCEPTION = "carInfoLoadingException";
    public static final String BOOKING_INFO_LOADING_EXCEPTION = "bookingInfoLoadingException";

    // -- Car Edit/Add -- //
    public static final String FAILED_EDIT_CAR_EXCEPTION = "failedEditCarException";
    public static final String FAILED_ADD_CAR_EXCEPTION = "failedAddCarException";
    public static final String WRONG_PRICE_INPUT_EXCEPTION = "wrongPriceInputException";
    public static final String PRICE_SIZE_OUT_OF_BOUNDS = "priceSizeOutOfBounds";
    public static final String DESCRIPTION_SIZE_OUT_OF_BOUNDS_EXCEPTION = "descriptionSizeOutOfBoundsException";
    public static final String MODEL_OUT_OF_BOUNDS_EXCEPTION = "modelOutOfBoundsException";
    public static final String PRICE_NEGATIVE_EXCEPTION = "PriceNegativeException";

    // -- Booking -- //

    public static final String PASSPORT_DATA_FORMAT_EXCEPTION = "passportDataFormatException";
    public static final String DATES_FORMAT_EXCEPTION = "datesFormatException";
    public static final String DATES_PERIOD_EXCEPTION = "datesPeriodException";
    public static final String UPDATING_BOOKING_STATUS_EXCEPTION = "updatingBookingStatusException";
    public static final String FAILED_ACCEPT_REQUEST_EXCEPTION = "failedAcceptRequestException";
    public static final String BOOKING_ALREADY_ON_REVIEW_EXCEPTION = "bookingAlreadyOnReviewException";
    public static final String FAILED_TAKE_ON_REVIEW_EXCEPTION = "failedTakeOnReviewException";
    public static final String FAILED_REGISTER_RETURN_EXCEPTION = "bookingAlreadyOnReviewException";
    public static final String FAILED_DECLINE_REQUEST_EXCEPTION = "failedDeclineRequestException";
    public static final String FAILED_ADDITIONAL_PAYMENT_EXCEPTION = "failedAdditionalPaymentException";
    public static final String CAR_ALREADY_BOOKED_EXCEPTION = "carAlreadyBookedException";

}

package com.pavlenko.kyrylo.controller.validator.statuses;

public final class RegistrationStatus {

    private RegistrationStatus() {
    }
    public static final String EMPTY_FIELD_EXCEPTION = "emptyFieldException";
    public static final String PASSWORD_SIZE_OUT_OF_BOUNDS_EXCEPTION = "passwordSizeOutOfBoundsException";
    public static final String PASSWORD_MATCH_EXCEPTION = "passwordMatchException";
    public static final String FIRST_NAME_SIZE_OUT_OF_BOUNDS_EXCEPTION = "firstnameSizeOutOfBoundsException";
    public static final String LAST_NAME_SIZE_OUT_OF_BOUNDS_EXCEPTION = "lastnameSizeOutOfBoundsException";
    public static final String EMAIL_SIZE_OUT_OF_BOUNDS_EXCEPTION = "emailSizeOutOfBoundsException";
    public static final String EMAIL_NOT_MATCH_PATTERN_EXCEPTION = "emailMatchPatternException";
    public static final String EMAIL_IS_RESERVED_EXCEPTION = "emailIsReserved";
    public static final String SUCCESSFUL_REGISTRATION = "successfulRegistration";
}

package com.pavlenko.kyrylo.controller.validator;

public class FieldValidator {

    private FieldValidator(){}

    public static boolean fieldIsEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }
}

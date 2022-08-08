package com.pavlenko.kyrylo.controller.command.impl.mapper;

import com.pavlenko.kyrylo.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public class UserDetailsMapper {

    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String REPASSWORD = "re-password";

    public UserDto fetchUserDtoFromRequest(HttpServletRequest req) {
        return new UserDto(
                req.getParameter(FIRSTNAME),
                req.getParameter(LASTNAME),
                req.getParameter(EMAIL),
                req.getParameter(PASSWORD),
                req.getParameter(REPASSWORD)
        );
    }
}

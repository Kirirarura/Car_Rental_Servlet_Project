package com.pavlenko.kyrylo.controller.command.impl.guest;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.controller.validator.UserValidator;
import com.pavlenko.kyrylo.controller.validator.statuses.RegistrationStatus;
import com.pavlenko.kyrylo.model.dto.UserDto;
import com.pavlenko.kyrylo.model.exeption.EmailIsAlreadyRegisteredException;
import com.pavlenko.kyrylo.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class PostRegistrationCommand implements Command {
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String REPASSWORD = "re-password";

    private final UserService userService;

    public PostRegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String firstname = request.getParameter(FIRSTNAME);
        String lastname = request.getParameter(LASTNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String repeatPassword = request.getParameter(REPASSWORD);
        UserDto userDto = new UserDto(firstname, lastname, email, password, repeatPassword);

        boolean userValid = UserValidator.validate(userDto, request);

        if (userValid) {
            try {
                userService.registerNewAccount(userDto);
                request.setAttribute("status", RegistrationStatus.SUCCESSFUL_REGISTRATION);
                return UriPath.REDIRECT + UriPath.LOGIN;
            } catch (EmailIsAlreadyRegisteredException e) {
                request.setAttribute("status", RegistrationStatus.EMAIL_IS_RESERVED_EXCEPTION);
            }
        }
        return JspFilePath.REGISTRATION;
    }
}

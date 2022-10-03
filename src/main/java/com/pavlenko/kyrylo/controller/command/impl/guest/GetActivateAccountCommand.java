package com.pavlenko.kyrylo.controller.command.impl.guest;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

public class GetActivateAccountCommand implements Command {

    private final UserService userService;

    public GetActivateAccountCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String email = request.getParameter("key1");

        try {
            boolean result = userService.accountVerification(email);
            if (result){
                return UriPath.REDIRECT + UriPath.LOGIN;
            } else {
                return UriPath.REDIRECT + UriPath.INDEX;
            }
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, StatusesContainer.REGISTRATION_EXCEPTION);
            return UriPath.REDIRECT + UriPath.REGISTRATION;
        }
    }
}

package com.pavlenko.kyrylo.controller.command.impl.guest;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.model.entity.User;
import com.pavlenko.kyrylo.model.exeption.AuthenticationException;
import com.pavlenko.kyrylo.model.exeption.UserIsBlockedException;
import com.pavlenko.kyrylo.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class PostLoginCommand implements Command {

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String USER_ID = "userId";
    private static final String ROLE = "role";
    private static final String AUTHENTICATION_EXCEPTION = "authenticationException";
    private static final String ACCOUNT_IS_BLOCKED_EXCEPTION = "accountIsBlocked";

    private final UserService userService;

    public PostLoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        try {
            User user = userService.authentication(email, password);
            request.getSession().setAttribute(USER_ID, user.getId());
            request.getSession().setAttribute(ROLE, user.getRole());
        } catch (UserIsBlockedException e) {
            request.setAttribute(ACCOUNT_IS_BLOCKED_EXCEPTION, true);
        } catch (AuthenticationException e) {
            request.setAttribute(AUTHENTICATION_EXCEPTION, true);
            request.setAttribute("status", "failedLogin");
            return JspFilePath.LOGIN;
        }

        return JspFilePath.CATALOG;
    }
}

package com.pavlenko.kyrylo.controller.command.impl.guest;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
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

            return UriPath.REDIRECT + UriPath.CATALOG;
        } catch (UserIsBlockedException e) {
            request.setAttribute("status", StatusesContainer.USER_BLOCKED_EXCEPTION);
            return JspFilePath.LOGIN;
        } catch (AuthenticationException e) {
            request.setAttribute("status", StatusesContainer.FAILED_LOGIN_EXCEPTION);
            return JspFilePath.LOGIN;
        }
    }
}

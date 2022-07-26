package com.pavlenko.kyrylo.controller.command.impl.common;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.model.entity.Role;

import javax.servlet.http.HttpServletRequest;

public class GetLogOutCommand implements Command {
    private static final String USER_ID = "userId";
    private static final String ROLE = "role";
    private static final String AUTH = "auth";
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(AUTH, null);
        request.getSession().setAttribute(USER_ID, null);
        request.getSession().setAttribute(ROLE, Role.RoleEnum.GUEST.name());

        return UriPath.REDIRECT + UriPath.LOGIN;
    }
}

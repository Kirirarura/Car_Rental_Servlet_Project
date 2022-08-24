package com.pavlenko.kyrylo.controller.command.impl.admin;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.model.entity.User;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

public class GetAllUsersCommand implements Command {

    private final UserService userService;
    private static final String USER_LIST = "userList";

    public GetAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> userList = null;
        try {
            userList = userService.findAllUsers();
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, "FindAllUsersException");
        }

        request.setAttribute(USER_LIST, userList);
        return JspFilePath.ADMIN_USERS;
    }
}

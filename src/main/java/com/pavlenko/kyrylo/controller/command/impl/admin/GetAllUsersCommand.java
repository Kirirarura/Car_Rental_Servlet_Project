package com.pavlenko.kyrylo.controller.command.impl.admin;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.model.entity.User;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.*;


/**
 * Returns user list page
 */
public class GetAllUsersCommand implements Command {
    private final UserService userService;


    public GetAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * Loads Customer list.
     */
    @Override
    public String execute(HttpServletRequest request) {
        try {
            List<User> userList = userService.findAllUsers();
            request.setAttribute(USER_LIST, userList);
        } catch (DataBaseException e) {
            request.setAttribute(STATUS, "FindAllUsersException");
        }


        return JspFilePath.ADMIN_USERS;
    }
}

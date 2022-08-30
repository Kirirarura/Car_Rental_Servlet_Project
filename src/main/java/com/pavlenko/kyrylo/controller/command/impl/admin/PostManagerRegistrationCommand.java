package com.pavlenko.kyrylo.controller.command.impl.admin;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.command.impl.mapper.UserDetailsMapper;
import com.pavlenko.kyrylo.controller.util.JspFilePath;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.controller.validator.UserValidator;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.dto.UserDto;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.exeption.EmailIsAlreadyRegisteredException;
import com.pavlenko.kyrylo.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static com.pavlenko.kyrylo.controller.util.ConstantsContainer.STATUS;

/**
 * Process registration of a new manager.
 */
public class PostManagerRegistrationCommand implements Command {
    private final UserDetailsMapper userDetailsMapper = new UserDetailsMapper();
    private final UserService userService;

    public PostManagerRegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        UserDto userDto = userDetailsMapper.fetchUserDtoFromRequest(request);
        boolean userValid = UserValidator.validate(userDto, request);

        if (userValid) {
            try {
                userService.registerNewManagerAccount(userDto);
                return UriPath.REDIRECT + UriPath.ADMIN_REGISTER_MANAGER;
            } catch (EmailIsAlreadyRegisteredException e) {
                request.setAttribute(STATUS, StatusesContainer.EMAIL_IS_RESERVED_EXCEPTION);
            } catch (DataBaseException e) {
                request.setAttribute(STATUS, StatusesContainer.REGISTRATION_EXCEPTION);
            }
        }
        return JspFilePath.ADMIN_MANAGER_REGISTRATION;
    }
}

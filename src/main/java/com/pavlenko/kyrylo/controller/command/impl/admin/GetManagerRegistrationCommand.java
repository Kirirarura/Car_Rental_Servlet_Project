package com.pavlenko.kyrylo.controller.command.impl.admin;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;

import javax.servlet.http.HttpServletRequest;

public class GetManagerRegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return JspFilePath.ADMIN_MANAGER_REGISTRATION;
    }
}

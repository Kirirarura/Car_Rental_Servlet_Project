package com.pavlenko.kyrylo.controller.command.impl.guest;


import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;

import javax.servlet.http.HttpServletRequest;

public class GetRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return JspFilePath.REGISTRATION;
    }
}

package com.pavlenko.kyrylo.controller.command.impl.common;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.JspFilePath;

import javax.servlet.http.HttpServletRequest;

public class GetCatalogCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return JspFilePath.CATALOG;
    }
}

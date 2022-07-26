package com.pavlenko.kyrylo.controller.util;

public final class JspFilePath {

    private JspFilePath() {}
    private static final String GUEST_DIR = "/jsp/guest/";
    public static final String INDEX = "/jsp/index.jsp";
    public static final String LOGIN = GUEST_DIR + "login.jsp";
    public static final String REGISTRATION = GUEST_DIR + "registration.jsp";
}

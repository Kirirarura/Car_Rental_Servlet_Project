package com.pavlenko.kyrylo.controller.util;

public final class JspFilePath {

    private JspFilePath() {
    }

    private static final String GUEST_DIR = "/jsp/guest/";
    private static final String ADMIN_DIR = "/jsp/admin/";
    private static final String CUSTOMER_DIR = "/jsp/customer/";
    private static final String MANAGER_DIR = "/jsp/manager/";
    private static final String COMMON_DIR = "/jsp/common/";
    public static final String INDEX = COMMON_DIR + "index.jsp";
    public static final String CATALOG = COMMON_DIR + "catalogPage.jsp";
    public static final String LOGIN = GUEST_DIR + "loginPage.jsp";
    public static final String REGISTRATION = GUEST_DIR + "registrationPage.jsp";
    public static final String ADMIN_USERS = ADMIN_DIR + "userListPage.jsp";
}

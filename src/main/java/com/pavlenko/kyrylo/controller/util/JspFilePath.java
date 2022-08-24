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
    public static final String ADMIN_MANAGER_REGISTRATION = ADMIN_DIR + "managerRegistration.jsp";
    public static final String ADMIN_CAR_MANAGEMENT = ADMIN_DIR + "carManagementPage.jsp";
    public static final String ADMIN_ADD_NEW_CAR = ADMIN_DIR + "addNewCarPage.jsp";
    public static final String CUSTOMER_RENT_CAR = CUSTOMER_DIR + "registerRentPage.jsp";
    public static final String CUSTOMER_REQUESTS = CUSTOMER_DIR + "myRentRequestsPage.jsp";
    public static final String MANAGER_ALL_REQUESTS = MANAGER_DIR + "allRequestsListPage.jsp";
    public static final String MANAGER_MY_REQUESTS = MANAGER_DIR + "managerRequestsPage.jsp";
    public static final String MANAGER_ERROR_PAGE = MANAGER_DIR + "requestСannotBeFulfilledPage.jsp";
    public static final String MANAGER_REGISTER_RETURN = MANAGER_DIR + "registerReturnPage.jsp";
 }

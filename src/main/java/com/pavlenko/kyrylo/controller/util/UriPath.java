package com.pavlenko.kyrylo.controller.util;

public final class UriPath {

    private UriPath() {}

    public static final String ROOT = "/";
    public static final String REDIRECT = "redirect:";
    public static final String CUSTOMER_PREFIX = "/Customer";
    public static final String MANAGER_PREFIX = "/Manager";
    public static final String ADMIN_PREFIX = "/Admin";
    public static final String STATIC_RESOURCES_PREFIX = "/static";
    public static final String INDEX = "/index";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String REGISTRATION = "/registration";
    public static final String CATALOG = "/catalog";
    public static final String ACTIVATE_ACCOUNT = "/ActivateAccount";
    public static final String ADMIN_USER_LIST = ADMIN_PREFIX + "/userList";
    public static final String ADMIN_BLOCK_UNBLOCK_USER = ADMIN_PREFIX + "/blockUnblock";
    public static final String ADMIN_REGISTER_MANAGER = ADMIN_PREFIX + "/registrationManager";
    public static final String ADMIN_CAR_EDIT = ADMIN_PREFIX + "/editCar";
    public static final String ADMIN_CAR_DELETE = ADMIN_PREFIX + "/deleteCar";
    public static final String ADMIN_ADD_NEW_CAR = ADMIN_PREFIX + "/addNewCar";
    public static final String CUSTOMER_CHECK_RENT = CUSTOMER_PREFIX + "/checkRent";
    public static final String CUSTOMER_RENT_CAR = CUSTOMER_PREFIX + "/rent";
    public static final String CUSTOMER_REQUESTS = CUSTOMER_PREFIX + "/myRequests";
    public static final String CUSTOMER_TERMINATE_REQUEST = CUSTOMER_PREFIX + "/terminate";
    public static final String CUSTOMER_ADDITIONAL_PAYMENT = CUSTOMER_PREFIX + "/additionalPayment";
    public static final String MANAGER_ALL_REQUESTS = MANAGER_PREFIX + "/allRequests";
    public static final String MANAGER_TAKE_ON_REVIEW = MANAGER_PREFIX + "/onReview";
    public static final String MANAGER_MY_REQUESTS = MANAGER_PREFIX + "/myRequests";
    public static final String MANAGER_ACCEPT_REQUEST = MANAGER_PREFIX + "/acceptRequest";
    public static final String MANAGER_REGISTER_RETURN = MANAGER_PREFIX + "/registerReturn";
    public static final String MANAGER_DECLINE_REQUEST = MANAGER_PREFIX + "/declineRequest";

}
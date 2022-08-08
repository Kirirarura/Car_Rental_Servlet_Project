package com.pavlenko.kyrylo.controller.util;

public final class UriPath {

    private UriPath() {}

    public static final String ROOT = "/";
    public static final String REDIRECT = "redirect:";
    public static final String USER_PREFIX = "/User";
    public static final String MANAGER_PREFIX = "/Manager";
    public static final String ADMIN_PREFIX = "/Admin";
    public static final String STATIC_RESOURCES_PREFIX = "/static";
    public static final String INDEX = "/index";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String REGISTRATION = "/registration";
    public static final String CATALOG = "/catalog";
    public static final String ADMIN_USER_LIST = ADMIN_PREFIX + "/userList";
    public static final String ADMIN_BLOCK_UNBLOCK_USER = ADMIN_PREFIX + "/blockUnblock";
    public static final String ADMIN_REGISTER_MANAGER = ADMIN_PREFIX + "/registrationManager";

}

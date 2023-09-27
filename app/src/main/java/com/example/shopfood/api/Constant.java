package com.example.shopfood.api;

public class Constant {

    public static final String URL_BASE = "http://172.16.49.184:3000";
    // user
    public static final String LOGIN_USER = "/login-user";
    public static final String CREATE_ACCOUNT = "/register-user";
    public static final String UPDATE_INFORMATION_ACCOUNT = "/update-information-user";
    public static final String UPDATE_CONTACT_ACCOUNT = "/update-contact-user";
    // food
    public static final String GET_LIST_FOOD = "/getList-food";
    public static final String GET_LIST_FOOD_BY_ID = "/getList-byId-food";

    public static final String UPDATE_AVATAR_USER = " /update-avatar-user";
    // cart
    public static final String ADD_TO_CART = "/insert-cart";
    public static final String DELETE_TO_CART = "/delete-cart";
    public static final String DELETE_BY_EMAIL_TO_CART = "/deleteAll-byEmail-cart";
    public static final String GET_TO_CART = "/getFood-cart";
    public static final String UPDATE_TO_CART = "/update-cart";

    // constant api status
    public static final String SUCCESS = "SUCCESS";
}

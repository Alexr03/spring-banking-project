package com.alexr03.dev.banking.models;

public enum ELogEvent {
    // Application
    APPLICATION_STARTED,

    // User events
    USER_REGISTERED,
    USER_LOGIN_SUCCESS,
    USER_LOGIN_FAILED,
    USER_BALANCE_TRANSFER,
    USER_BALANCE_CHECK,

    // Staff events
    STAFF_LOGIN_SUCCESS,
    STAFF_LOGIN_FAILED,
}

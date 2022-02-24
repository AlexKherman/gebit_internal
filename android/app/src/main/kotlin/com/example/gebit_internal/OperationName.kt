package com.example.gebit_internal

enum class OperationName(private val message:String) {
    CREATED(""),
    ERROR(""),
    GET_AUTH_TOKEN(""),
    AUTHORIZATION(""),
    CLIENT_TOKEN_WAS_NOT_SET(""),
    CLIENT_TOKEN_NOT_EXIST(""),
    AUTH_TOKEN_NOT_EXIST(""),
    PAYMENT_APPROVED(""),
    PAYMENT_COMPLETED(""),
    PAYMENT_CANCELLED("")
}
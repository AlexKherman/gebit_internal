package com.example.gebit_internal.model

data class Payment(
    var paymentId: String? = null,
    val authorizationToken:String? = null,
    var clientToken :String? = null,
    var customerToken:String? = null
)

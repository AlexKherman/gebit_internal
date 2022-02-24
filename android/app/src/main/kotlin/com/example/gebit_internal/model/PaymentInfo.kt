package com.example.gebit_internal.model

data class PaymentInfo(
    var paymentId: String? = null,
    val authorizationToken:String? = null,
    var clientToken :String? = null,
    var customerToken:String? = null
)

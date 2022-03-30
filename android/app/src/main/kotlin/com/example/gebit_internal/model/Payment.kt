package com.example.gebit_internal.model

import com.example.gebit_internal.model.response.AmountResponseEntity

data class Payment(
    var paymentSum: Int = 0,
    val authorizationToken:String? = null,
    var clientToken :String? = null,
    var paymentId: String? = null,
    var paymentDetails: String? = null,
    var customerToken:String? = null,
    var currentCategoryIndex: Int = 0,
    var amount: AmountResponseEntity? = AmountResponseEntity("", 0, 0)
)
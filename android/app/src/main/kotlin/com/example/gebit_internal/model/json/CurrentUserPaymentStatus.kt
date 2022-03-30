package com.example.gebit_internal.model.json

data class CurrentUserPaymentStatus(
    val paymentCurrency: String?,
    val paymentAmount: Int?,
    val paymentID: String?,
    val transactionId: String?,
    val clientId: String?
)

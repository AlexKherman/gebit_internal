package com.example.gebit_internal.model.request

import com.example.gebit_internal.model.request.AmountRequestEntity
import com.google.gson.annotations.SerializedName

data class UpdateRequestEntity(
    @SerializedName("paymentId")
    val paymentID: String,
    @SerializedName("transactionId")
    val transactionID: String,
    @SerializedName("clientId")
    val clientID: String,
    @SerializedName("clientTransactionId")
    val clientTransactionId: String,
    @SerializedName("amount")
    val amount: AmountRequestEntity,
    @SerializedName("paymentStatus")
    val paymentStatus: String,
    @SerializedName("successURL")
    val successURL: String,
    @SerializedName("cancelURL")
    val cancelURL: String,
    @SerializedName("failureURL")
    val failureURL: String,
    @SerializedName("paymentDetails")
    val paymentDetails: String
)

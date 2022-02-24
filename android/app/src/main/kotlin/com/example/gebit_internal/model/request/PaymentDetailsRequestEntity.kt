package com.example.gebit_internal.model.request

import com.example.gebit_internal.model.request.AmountRequestEntity
import com.example.gebit_internal.model.request.ArticlePositionRequestEntity
import com.google.gson.annotations.SerializedName

data class PaymentDetailsRequestEntity(
    @SerializedName("paymentContext")
    val paymentContext: String,
    @SerializedName("referenceId")
    val referenceID: String,
    @SerializedName("clientId")
    val clientID: String,
    @SerializedName("clientTransactionId")
    val clientTransactionID: String,
    @SerializedName("paymentDetails")
    val paymentDetails: String,
    @SerializedName("referencePaymentId")
    val referencePaymentID: String,
    @SerializedName("paymentProvider")
    val paymentProvider: String,
    @SerializedName("paymentVariant")
    val paymentVariant: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("amount")
    val amount: AmountRequestEntity,
    @SerializedName("articlePositions")
    val articlePositions:List<ArticlePositionRequestEntity>,
    @SerializedName("automaticCapture")
    val automaticCapture: Boolean
)

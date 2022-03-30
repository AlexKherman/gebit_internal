package com.example.gebit_internal.model.response

import com.google.gson.annotations.SerializedName

data class CompleteResponseEntity(
    @SerializedName("paymentId")
    val paymentId: String?,
    @SerializedName("voidedPaymentId")
    val voidedPaymentID: String?,
    @SerializedName("referencedPayment")
    val referencedPayment: String?,
    @SerializedName("paymentContext")
    val paymentContext: String?,
    @SerializedName("referenceId")
    val referenceID: String?,
    @SerializedName("referencePaymentId")
    val referencePaymentID: String?,
    @SerializedName("clientId")
    val clientID: String?,
    @SerializedName("clientTransactionID")
    val clientTransactionId: String?,
    @SerializedName("amount")
    val amount: AmountResponseEntity?,
    @SerializedName("paymentDetails")
    val paymentDetails: String?,
    @SerializedName("paymentStatus")
    val paymentStatus: String?,
    @SerializedName("transactionId")
    val transactionID: String?,
    @SerializedName("paymentData")
    val paymentData: String?,
    @SerializedName("paymentProvider")
    val paymentProvider: String?,
    @SerializedName("paymentVariant")
    val paymentVariant: String?,
    @SerializedName("paymentVariant2")
    val welcomeDescription: String?,
    @SerializedName("articlePositions")
    val articlePositions: List<AmountResponseEntity>?,
    @SerializedName("errorMessage")
    val errorMessage: String?,
    @SerializedName("errorCode")
    val errorCode: String?,
    @SerializedName("approvalURL")
    val approvalURL: String?,
    @SerializedName("transactionData")
    val transactionData: String?,
    @SerializedName("successURL")
    val successURL: String?,
    @SerializedName("cancelURL")
    val cancelURL: String?,
    @SerializedName("notificationCallback")
    val notificationCallback: String?
)
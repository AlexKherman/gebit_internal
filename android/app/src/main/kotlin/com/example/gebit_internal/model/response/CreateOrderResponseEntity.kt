package com.example.gebit_internal.model.response

import com.example.gebit_internal.model.response.AmountResponseEntity
import com.example.gebit_internal.model.response.ArticlePositionResponseEntity
import com.google.gson.annotations.SerializedName

data class CreateOrderResponseEntity(
    @SerializedName("paymentId")
    val paymentID: String?,
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
    @SerializedName("clientID")
    val clientId: String?,
    @SerializedName("clientTransactionId")
    val clientTransactionID: String?,
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
    @SerializedName("welcomeDescription")
    val welcomeDescription: String?,
    @SerializedName("articlePositions")
    val articlePositions: List<ArticlePositionResponseEntity>?,
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

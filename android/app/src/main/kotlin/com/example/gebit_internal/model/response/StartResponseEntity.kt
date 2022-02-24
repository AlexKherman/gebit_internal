package com.example.gebit_internal.model.response

import com.example.gebit_internal.model.response.AmountResponseEntity
import com.example.gebit_internal.model.response.ArticlePositionResponseEntity
import com.google.gson.annotations.SerializedName

data class StartResponseEntity(
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
    @SerializedName("clientId")
    val clientID: String?,
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
    @SerializedName("description")
    val description: String?,
    @SerializedName("articlePositions")
    val articlePositionEntity: List<ArticlePositionResponseEntity>?,
    @SerializedName("errorMessage")
    val errorMessage: String?,
    @SerializedName("errorCode")
    val errorCode: String?,
    @SerializedName("approvalUrl")
    val approvalUrl: String?,
    @SerializedName("transactionData")
    val transactionData: String?,
    @SerializedName("successUrl")
    val successUrl: String?,
    @SerializedName("cancelUrl")
    val cancelUrl: String?,
    @SerializedName("failureUrl")
    val failureUrl: String?,
    @SerializedName("notificationCallback")
    val notificationCallback: String?
)

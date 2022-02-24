package com.example.gebit_internal.model.request

import com.google.gson.annotations.SerializedName

data class CompleteEntity(
    @SerializedName("paymentId")
    val paymentID:String,
    @SerializedName("clientTransactionId")
    val clientTransactionID:String,
    @SerializedName("clientId")
    val clientID:String,
    @SerializedName("paymentDetails")
    val paymentDetails:String
)

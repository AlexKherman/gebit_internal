package com.example.gebit_internal.model.request

import com.example.gebit_internal.model.request.PaymentDetailsRequestEntity
import com.google.gson.annotations.SerializedName

data class StartRequestEntity(
    @SerializedName("paymentDetails")
    val paymentDetailsRequest: PaymentDetailsRequestEntity
)

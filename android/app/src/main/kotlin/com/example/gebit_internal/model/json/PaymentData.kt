package com.example.gebit_internal.model.json

import com.example.gebit_internal.model.json.PaymentCategory
import com.google.gson.annotations.SerializedName

data class PaymentData(
    @SerializedName("session_id")
    val sessionID:String,
    @SerializedName("client_token")
    val clientToken:String,
    @SerializedName("payment_method_categories")
    val paymentMethodCategories: List<PaymentCategory>

)

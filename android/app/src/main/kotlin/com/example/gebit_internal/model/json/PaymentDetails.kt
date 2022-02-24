package com.example.gebit_internal.model.json

import com.google.gson.annotations.SerializedName

data class PaymentDetails (
    @SerializedName("customer_token")
    val customerToken: String? = null,
    @SerializedName("authorization_token")
    val authorizationToken :String,
    @SerializedName("purchase_currency")
    val purchaseCurrency:String = "EUR",
    @SerializedName("purchase_country")
    val purchaseCountry:String = "DE",
    @SerializedName("locale")
    val locale:String = "en-GB",
    @SerializedName("billing_address")
    val billingAddress:String ="",
    @SerializedName("description")
    val description:String = "MySaaS subscription",
    @SerializedName("intended_use")
    val intendedUse:String = "subscription"
)
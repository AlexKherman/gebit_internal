package com.example.gebit_internal.model.json

import android.service.carrier.CarrierIdentifier
import com.google.gson.annotations.SerializedName

data class PaymentCategory(
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("name")
    val  name:String
)

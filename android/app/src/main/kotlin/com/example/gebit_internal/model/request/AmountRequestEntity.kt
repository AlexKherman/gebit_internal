package com.example.gebit_internal.model.request

import com.google.gson.annotations.SerializedName

data class AmountRequestEntity(
    @SerializedName("currency")
    val currency:String,
    @SerializedName("value")
    val value : Int,
    @SerializedName("scale")
    val scale: Int
)

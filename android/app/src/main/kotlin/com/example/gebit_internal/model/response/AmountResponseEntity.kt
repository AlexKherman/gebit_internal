package com.example.gebit_internal.model.response

import com.google.gson.annotations.SerializedName

data class AmountResponseEntity(
    @SerializedName("currency")
    val currency:String?,
    @SerializedName("value")
    val value : Int?,
    @SerializedName("scale")
    val scale: Int?
)

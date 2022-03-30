package com.example.gebit_internal.model.response

import com.google.gson.annotations.SerializedName

data class AmountResponseEntity(
    @SerializedName("currency")
    var currency:String?,
    @SerializedName("value")
    var value : Int?,
    @SerializedName("scale")
    var scale: Int?
)

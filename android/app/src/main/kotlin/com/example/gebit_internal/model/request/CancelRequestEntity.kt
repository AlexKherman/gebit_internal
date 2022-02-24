package com.example.gebit_internal.model.request

import com.google.gson.annotations.SerializedName

data class CancelRequestEntity(
    @SerializedName("voidRequest")
    val voidRequest: VoidRequestEntity
)

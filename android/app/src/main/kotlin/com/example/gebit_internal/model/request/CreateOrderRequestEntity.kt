package com.example.gebit_internal.model.request

import com.google.gson.annotations.SerializedName

data class CreateOrderRequestEntity(
    @SerializedName("updateRequest")
    val updateRequest: UpdateRequestEntity
)

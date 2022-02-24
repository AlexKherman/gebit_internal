package com.example.gebit_internal.model.request

import com.example.gebit_internal.model.request.CompleteEntity
import com.google.gson.annotations.SerializedName

data class CompleteRequestEntity(
    @SerializedName("completeRequest")
    val completeRequest: CompleteEntity
)

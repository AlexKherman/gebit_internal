package com.example.gebit_internal.model.response

import com.google.gson.annotations.SerializedName

data class ErrorResponseEntity(
    @SerializedName("name")
    val name: String,
    @SerializedName("detail")
    val detail: String
)

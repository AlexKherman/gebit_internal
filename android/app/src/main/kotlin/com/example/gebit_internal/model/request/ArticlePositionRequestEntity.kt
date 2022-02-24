package com.example.gebit_internal.model.request

import com.example.gebit_internal.model.request.AmountRequestEntity
import com.google.gson.annotations.SerializedName

data class ArticlePositionRequestEntity(
    @SerializedName("number")
    val number: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("unitPrice")
    val unitPrice: AmountRequestEntity,
    @SerializedName("taxRate")
    val taxRate: Int,
    @SerializedName("totalAmount")
    val totalAmount: AmountRequestEntity,
    @SerializedName("totalDiscountAmount")
    val totalDiscountAmount: AmountRequestEntity,
    @SerializedName("totalTaxAmount")
    val totalTaxAmount: AmountRequestEntity
)

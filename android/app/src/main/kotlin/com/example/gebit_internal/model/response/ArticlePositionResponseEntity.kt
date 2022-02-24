package com.example.gebit_internal.model.response

import com.example.gebit_internal.model.response.AmountResponseEntity
import com.google.gson.annotations.SerializedName

data class ArticlePositionResponseEntity(
    @SerializedName("number")
    val number: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("quantity")
    val quantity: Int?,
    @SerializedName("unit")
    val unit: String?,
    @SerializedName("unitPrice")
    val unitPrice: AmountResponseEntity?,
    @SerializedName("taxRate")
    val taxRate: Int?,
    @SerializedName("totalAmount")
    val totalAmount: AmountResponseEntity?,
    @SerializedName("totalDiscountAmount")
    val totalDiscountAmount: AmountResponseEntity?,
    @SerializedName("totalTaxAmount")
    val totalTaxAmount: AmountResponseEntity?
)
package com.example.gebit_internal.model.json

import com.example.gebit_internal.OperationName

data class OperationStatus(
    var operationName: OperationName,
    var paymentStatus: Boolean,
    var paymentID: String?,
    var error: String?
)
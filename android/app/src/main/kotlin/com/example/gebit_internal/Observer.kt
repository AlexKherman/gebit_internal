package com.example.gebit_internal

import com.example.gebit_internal.model.json.CurrentUserPaymentStatus
import com.example.gebit_internal.model.json.OperationStatus
import com.google.gson.Gson

object Observer {

    private val list = mutableListOf<Subscriber>()
    private var currentStatus = ""
    private var currentData: String = ""

    fun addSubscriber(subscriber: Subscriber) {
        list.add(subscriber)
    }

    fun updateData(data: String) {
        list.forEach {
            it.update(data)
        }
        currentData = data
    }

    fun getCurrentUserPaymentStatus(
        paymentCurrency: String?,
        paymentAmount: Int?,
        paymentID: String?,
        transactionId: String?,
        clientId: String?
    ) {
        val currentUserPaymentStatus = CurrentUserPaymentStatus(paymentCurrency, paymentAmount, paymentID, transactionId, clientId)
        currentStatus = Gson().toJson(currentUserPaymentStatus)
    }

    fun getOperationStatus(
        operationName: OperationName,
        paymentStatus: Boolean,
        paymentID: String?,
        error: String?
    ) {
        val operationStatus = OperationStatus(operationName, paymentStatus, paymentID, error)
        currentStatus = Gson().toJson(operationStatus)
    }

    fun removeSub(subscriber: Subscriber) {
        list.remove(subscriber)
    }
}

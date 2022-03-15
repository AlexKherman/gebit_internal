package com.example.gebit_internal.api

import com.example.gebit_internal.model.Payment
import com.example.gebit_internal.model.json.PaymentDetails
import com.example.gebit_internal.model.request.AmountRequestEntity
import com.example.gebit_internal.model.request.ArticlePositionRequestEntity
import com.example.gebit_internal.model.request.CancelRequestEntity
import com.example.gebit_internal.model.request.CompleteEntity
import com.example.gebit_internal.model.request.CompleteRequestEntity
import com.example.gebit_internal.model.request.CreateOrderRequestEntity
import com.example.gebit_internal.model.request.PaymentDetailsRequestEntity
import com.example.gebit_internal.model.request.StartRequestEntity
import com.example.gebit_internal.model.request.UpdateRequestEntity
import com.example.gebit_internal.model.request.VoidRequestEntity
import com.example.gebit_internal.model.response.CancelResponseEntity
import com.example.gebit_internal.model.response.CompleteResponseEntity
import com.example.gebit_internal.model.response.CreateOrderResponseEntity
import com.example.gebit_internal.model.response.StartResponseEntity
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.util.UUID

class Repo(private val service: Api, var authorizationToken: String?) {

    val gson = Gson()

    val paymentInfo = Payment()

    suspend fun start(): Response<StartResponseEntity> = withContext(Dispatchers.IO)

    {
        val startRequestEntity = StartRequestEntity(
            PaymentDetailsRequestEntity(
                paymentContext = "mobileScanning",
                referenceID = "someBasketId",
                clientID = "",
                clientTransactionID = UUID.randomUUID().toString(),
                paymentDetails = "",
                referencePaymentID = "someReferencePaymentId",
                paymentProvider = "klarna",
                paymentVariant = "klarna",
                description = "some description",
                amount = AmountRequestEntity(
                    currency = "EUR",
                    value = 5000000,
                    scale = 2
                ),
                articlePositions = listOf(
                    ArticlePositionRequestEntity(
                        number = "19-402-USA",
                        name = "Red T-Shirt",
                        quantity = 5,
                        unit = "pcs",
                        unitPrice = AmountRequestEntity(currency = "EUR", value = 10000, scale = 0),
                        taxRate = 1000,
                        totalAmount = AmountRequestEntity(currency = "EUR", value = 50000, scale = 0),
                        totalDiscountAmount = AmountRequestEntity(currency = "EUR", value = 0, scale = 0),
                        totalTaxAmount = AmountRequestEntity(currency = "EUR", value = 4545, scale = 0)
                    )
                ),
                automaticCapture = false
            )
        )

        service.start(startRequestEntity, authorizationToken)
    }

    suspend fun createOrder(): Response<CreateOrderResponseEntity> {
        val paymentDetails = PaymentDetails(authorizationToken = paymentInfo.authorizationToken ?: "")
        val createOrderRequestEntity = CreateOrderRequestEntity(
            updateRequest = UpdateRequestEntity(
                paymentID = paymentInfo.paymentId ?: "",
                transactionID = "aca4eada-273d-4b07-8a97-6888e596adaa",
                clientID = "",
                clientTransactionId = UUID.randomUUID().toString(),
                amount = AmountRequestEntity(
                    currency = "EUR",
                    value = 230,
                    scale = 2
                ),
                paymentStatus = "APPROVED",
                successURL = "https://www.gebit.de/result",
                cancelURL = "https://www.gebit.de/cancel",
                failureURL = "https://www.gebit.de/cancel",
                paymentDetails = gson.toJson(paymentDetails)
            )
        )
        return withContext(Dispatchers.IO) { service.createOrder(createOrderRequestEntity, authorizationToken) }
    }

    suspend fun finalize(): Response<CompleteResponseEntity> {
        val paymentDetails = PaymentDetails(
            customerToken = paymentInfo.customerToken,
            authorizationToken = paymentInfo.authorizationToken ?: ""
        )
        val completeRequestEntity = CompleteRequestEntity(
            completeRequest = CompleteEntity(
                paymentID = paymentInfo.paymentId ?: "",
                clientTransactionID = UUID.randomUUID().toString(),
                clientID = "",
                paymentDetails = gson.toJson(paymentDetails)
            )
        )
        return withContext(Dispatchers.IO) { service.complete(completeRequestEntity, authorizationToken) }
    }

    suspend fun cancel(): Response<CancelResponseEntity> {
        val paymentDetails = PaymentDetails(
            customerToken = paymentInfo.customerToken,
            authorizationToken = paymentInfo.authorizationToken ?: ""
        )
        val cancelRequestEntity = CancelRequestEntity(
            voidRequest = VoidRequestEntity(
                paymentID = paymentInfo.paymentId ?: "",
                clientTransactionID = "1",
                clientID = "",
                paymentDetails = gson.toJson(paymentDetails)
            )
        )
        return withContext(Dispatchers.IO) { service.cancel(cancelRequestEntity, authorizationToken) }
    }
}

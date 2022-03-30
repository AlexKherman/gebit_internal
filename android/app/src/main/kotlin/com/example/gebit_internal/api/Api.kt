package com.example.gebit_internal.api

import com.example.gebit_internal.model.request.CancelRequestEntity
import com.example.gebit_internal.model.request.CompleteRequestEntity
import com.example.gebit_internal.model.request.CreateOrderRequestEntity
import com.example.gebit_internal.model.request.StartRequestEntity
import com.example.gebit_internal.model.response.CancelResponseEntity
import com.example.gebit_internal.model.response.CompleteResponseEntity
import com.example.gebit_internal.model.response.CreateOrderResponseEntity
import com.example.gebit_internal.model.response.StartResponseEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {

    @POST("online/start")
    suspend fun start(
        @Body startRequestEntity: StartRequestEntity,
        @Header("X-RP-Authentication") authorizationKey: String?
    ): Response<StartResponseEntity>

    @POST("online/void")
    suspend fun cancel(
        @Body cancelRequestEntity: CancelRequestEntity
    ): Response<CancelResponseEntity>

    @POST("online/complete")
    suspend fun complete(
        @Body completeRequestEntity: CompleteRequestEntity
    ): Response<CompleteResponseEntity>

    @POST("online/update")
    suspend fun createOrder(
        @Body createOrderRequestEntity: CreateOrderRequestEntity
    ): Response<CreateOrderResponseEntity>
}
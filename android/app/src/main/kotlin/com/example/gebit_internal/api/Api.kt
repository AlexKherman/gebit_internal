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
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {

    @POST("online/start")
//    @Headers("X-RP-Authentication: eyJ0eXAiOiJKV1QiLCJzdWJqZWN0VHlwZSI6ImN1c3RvbWVyIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJlMTdiYTllNC1iMDkzLTRkMGItOTQ4Ni0xNjEzNmE3NTlmNmQiLCJyb2xlcyI6W10sImlzcyI6ImN1c3RvbWVyLWFwaSIsImlhdCI6MTYyOTIwMDc3Nn0.Ts_Wqlp35TsKHrrFz3OXA702wxRa9gX6x9N9BBWfPe8")
    suspend fun start(
        @Body startRequestEntity: StartRequestEntity,
        @Header("X-RP-Authentication") authorizationKey: String?
    ): Response<StartResponseEntity>

    @POST("online/void")
    suspend fun cancel(
        @Body cancelRequestEntity: CancelRequestEntity,
        @Header("X-RP-Authentication") authorizationKey: String?
    ): Response<CancelResponseEntity>

    @POST("online/complete")
    suspend fun complete(
        @Body completeRequestEntity: CompleteRequestEntity,
        @Header("X-RP-Authentication") authorizationKey: String?
    ): Response<CompleteResponseEntity>

    @POST("online/update")
    fun createOrder(
        @Body createOrderRequestEntity: CreateOrderRequestEntity,
        @Header("X-RP-Authentication") authorizationKey: String?
    ): Response<CreateOrderResponseEntity>
}
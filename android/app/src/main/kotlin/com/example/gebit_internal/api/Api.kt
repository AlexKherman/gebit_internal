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
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {

    @POST("online/start")
    @Headers("X-RP-Authentication: eyJ0eXAiOiJKV1QiLCJzdWJqZWN0VHlwZSI6ImN1c3RvbWVyIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJlMTdiYTllNC1iMDkzLTRkMGItOTQ4Ni0xNjEzNmE3NTlmNmQiLCJyb2xlcyI6W10sImlzcyI6ImN1c3RvbWVyLWFwaSIsImlhdCI6MTYyOTIwMDc3Nn0.Ts_Wqlp35TsKHrrFz3OXA702wxRa9gX6x9N9BBWfPe8")
    suspend fun start(
        @Body startRequestEntity: StartRequestEntity
    ): Response<StartResponseEntity>

    @POST("online/void")
    @Headers("X-RP-Authentication: eyJ0eXAiOiJKV1QiLCJzdWJqZWN0VHlwZSI6ImN1c3RvbWVyIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJlMTdiYTllNC1iMDkzLTRkMGItOTQ4Ni0xNjEzNmE3NTlmNmQiLCJyb2xlcyI6W10sImlzcyI6ImN1c3RvbWVyLWFwaSIsImlhdCI6MTYyOTIwMDc3Nn0.Ts_Wqlp35TsKHrrFz3OXA702wxRa9gX6x9N9BBWfPe8")
    suspend fun cancel(@Body cancelRequestEntity: CancelRequestEntity):Response<CancelResponseEntity>

    @POST("online/complete")
    @Headers("X-RP-Authentication: eyJ0eXAiOiJKV1QiLCJzdWJqZWN0VHlwZSI6ImN1c3RvbWVyIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJlMTdiYTllNC1iMDkzLTRkMGItOTQ4Ni0xNjEzNmE3NTlmNmQiLCJyb2xlcyI6W10sImlzcyI6ImN1c3RvbWVyLWFwaSIsImlhdCI6MTYyOTIwMDc3Nn0.Ts_Wqlp35TsKHrrFz3OXA702wxRa9gX6x9N9BBWfPe8")
    suspend fun complete(@Body completeRequestEntity: CompleteRequestEntity):Response<CompleteResponseEntity>

    @POST("online/update")
    @Headers("X-RP-Authentication: eyJ0eXAiOiJKV1QiLCJzdWJqZWN0VHlwZSI6ImN1c3RvbWVyIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJlMTdiYTllNC1iMDkzLTRkMGItOTQ4Ni0xNjEzNmE3NTlmNmQiLCJyb2xlcyI6W10sImlzcyI6ImN1c3RvbWVyLWFwaSIsImlhdCI6MTYyOTIwMDc3Nn0.Ts_Wqlp35TsKHrrFz3OXA702wxRa9gX6x9N9BBWfPe8")
    fun createOrder(@Body createOrderRequestEntity: CreateOrderRequestEntity):Response<CreateOrderResponseEntity>
}
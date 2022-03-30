package com.example.gebit_internal.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://042d-178-127-145-163.ngrok.io/payment/api/payments/")//http://localhost:9000
        .addConverterFactory(GsonConverterFactory.create(
            GsonBuilder().serializeNulls().create()
        ))
        .build()

    val service = retrofit.create(Api::class.java)
}
package com.example.gebit_internal.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://fbcf-86-57-235-139.ngrok.io/payment/api/payments/")//http://localhost:9000
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(Api::class.java)
}
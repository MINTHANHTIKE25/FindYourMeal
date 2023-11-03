package com.example.findyourmeal.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private var client = OkHttpClient.Builder().addNetworkInterceptor(Interceptor { chain ->
        val request = chain.request().newBuilder().build()
        chain.proceed(request)
    }).build()
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
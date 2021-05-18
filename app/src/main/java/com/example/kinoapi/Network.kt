package com.example.kinoapi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network{
    private const val BASE_URL = "https://kinopoiskapiunofficial.tech"
    private const val API_KEY = "06e6c6a0-6bd0-4d26-9473-d39fa28f75bb"

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            // Добавляем к заголовкам запросов ключ доступа к API
            val request = chain.request().newBuilder()
                .addHeader("X-API-KEY", API_KEY)
                .build()
            chain.proceed(request)
        }.build()

    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    public val apiService = retrofit.create(KinopoiskApiService::class.java)
}
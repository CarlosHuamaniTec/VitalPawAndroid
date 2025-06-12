package com.example.vitalpaw.data.service

import com.example.vitalpaw.data.model.LocalDateAdapter
import com.example.vitalpaw.data.model.LocalDateTimeAdapter
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime

object RetrofitClient {
    private const val BASE_URL = "https://vitalpaw.app/api/"
    private const val API_KEY = "fsadbasdfasdfas812234u8001234140124214"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-API-Key", API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()

    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter)
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter)
        .create()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}
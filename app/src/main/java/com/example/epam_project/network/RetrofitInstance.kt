package com.example.epam_project.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.spaceflightnewsapi.net/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ArticleApiService = retrofit.create(ArticleApiService::class.java)
}

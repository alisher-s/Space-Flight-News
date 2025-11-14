package com.example.epam_project.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApiService {
    @GET("v4/articles/")
    fun getArticles(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<PaginatedResponse<SpaceArticle>>
}

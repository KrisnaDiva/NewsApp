package com.krisna.diva.newsapp.data.remote.retrofit

import com.krisna.diva.newsapp.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v2/everything")
    suspend fun getAllNews(
        @Query("q") query: String = "indonesia"
    ): NewsResponse

    @GET("/v2/top-headlines")
    suspend fun getHeadlineNews(
        @Query("country") query: String = "us"
    ): NewsResponse
}
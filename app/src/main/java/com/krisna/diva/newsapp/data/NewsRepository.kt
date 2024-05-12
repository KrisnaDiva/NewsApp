package com.krisna.diva.newsapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import androidx.paging.map
import com.google.gson.Gson
import com.krisna.diva.newsapp.data.remote.response.ArticlesItem
import com.krisna.diva.newsapp.data.remote.response.NewsResponse
import com.krisna.diva.newsapp.data.remote.retrofit.ApiService
import retrofit2.HttpException

class NewsRepository private constructor(private var apiService: ApiService) {

    fun getAllNews() = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllNews()
            val articles = response.articles
            if (articles != null) {
                val articleList = articles.map { article -> article }
                emit(Result.Success(articleList))
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, NewsResponse::class.java)
            emit(errorResponse.status?.let { Result.Error(it) })
        }
    }

    fun getHeadlineNews() = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getHeadlineNews()
            val articles = response.articles
            if (articles != null) {
                val articleList = articles.map { article -> article }
                emit(Result.Success(articleList))
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, NewsResponse::class.java)
            emit(errorResponse.status?.let { Result.Error(it) })
        }
    }

    companion object {
        @Volatile
        private var instance: NewsRepository? = null
        fun getInstance(
            apiService: ApiService
        ) =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(apiService)
            }.also { instance = it }
    }
}
package com.krisna.diva.newsapp.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.krisna.diva.newsapp.data.remote.response.ArticlesItem
import com.krisna.diva.newsapp.data.remote.retrofit.ApiService

class NewsRepository private constructor(private var apiService: ApiService) {

    fun getAllNews(): LiveData<PagingData<ArticlesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                AllNewsPagingSource(apiService)
            }
        ).liveData
    }

    fun getHeadlineNews(): LiveData<PagingData<ArticlesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                HeadlineNewsPagingSource(apiService)
            }
        ).liveData
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
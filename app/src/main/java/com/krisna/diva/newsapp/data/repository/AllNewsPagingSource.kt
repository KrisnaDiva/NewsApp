package com.krisna.diva.newsapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.krisna.diva.newsapp.data.remote.response.ArticlesItem
import com.krisna.diva.newsapp.data.remote.retrofit.ApiService

class AllNewsPagingSource(private val apiService: ApiService) : PagingSource<Int, ArticlesItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {

        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val response = apiService.getAllNews()
            val responseData = response.articles?.filterNotNull() ?: emptyList()
            LoadResult.Page(
                data = responseData,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}
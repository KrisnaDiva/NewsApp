package com.krisna.diva.newsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.krisna.diva.newsapp.data.NewsRepository
import com.krisna.diva.newsapp.data.Result
import com.krisna.diva.newsapp.data.remote.response.ArticlesItem

class MainViewModel(repository: NewsRepository) : ViewModel() {
    val listAllNews: LiveData<PagingData<ArticlesItem>> = repository.getAllNews().cachedIn(viewModelScope)
    val listHeadlineNews: LiveData<PagingData<ArticlesItem>> = repository.getHeadlineNews().cachedIn(viewModelScope)
}
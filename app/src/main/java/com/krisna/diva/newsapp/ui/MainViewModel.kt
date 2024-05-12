package com.krisna.diva.newsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.krisna.diva.newsapp.data.NewsRepository
import com.krisna.diva.newsapp.data.Result
import com.krisna.diva.newsapp.data.remote.response.ArticlesItem

class MainViewModel(repository: NewsRepository) : ViewModel() {
    val listAllNews: LiveData<Result<List<ArticlesItem?>>?> = repository.getAllNews()
    val listHeadlineNews: LiveData<Result<List<ArticlesItem?>>?> = repository.getHeadlineNews()
}
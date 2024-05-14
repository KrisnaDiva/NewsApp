package com.krisna.diva.newsapp.di

import com.krisna.diva.newsapp.data.repository.NewsRepository
import com.krisna.diva.newsapp.data.remote.retrofit.ApiConfig
import com.krisna.diva.newsapp.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ApiConfig.getApiService() }
    single { NewsRepository.getInstance(get()) }
    viewModel { MainViewModel(get()) }
}
package com.example.mynews.repository

import android.content.Context
import com.example.mynews.R
import com.example.mynews.models.NewsResponse
import com.example.mynews.network.NewsApiService

class NewsRepository(private val apiService: NewsApiService, private val context: Context) {
    suspend fun getNews(): NewsResponse {
        val apiKey = context.getString(R.string.news_api_key)
        return apiService.getTopHeadlines(apiKey = apiKey)
    }
}

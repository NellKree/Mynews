package com.example.mynews.models

data class NewsResponse(val articles: List<Article>)

data class Article(
    val title: String,
    val description: String?,
    val urlToImage: String?,
    var content: String?
)

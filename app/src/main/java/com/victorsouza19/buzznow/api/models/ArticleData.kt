package com.victorsouza19.buzznow.api.models

data class ArticleData(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

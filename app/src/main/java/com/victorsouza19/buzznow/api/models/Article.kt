package com.victorsouza19.buzznow.api.models

data class Article(
    var author: String,
    var title: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var description: String
)

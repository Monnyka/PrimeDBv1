package com.nyka.primedb.model

data class TrendingMovie(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
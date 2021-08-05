package com.nyka.primedb.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "trendingMovie"
)
data class TrendingMovie(
    @PrimaryKey
    val id : Int?,
    val page: Int,
    val results: List<TrendingMovieResult>,
    val total_pages: Int,
    val total_results: Int
)
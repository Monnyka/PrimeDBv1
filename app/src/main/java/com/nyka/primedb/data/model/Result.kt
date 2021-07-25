package com.nyka.primedb.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Result(
//    val adult: Boolean,
    val backdrop_path: String,
//    val genre_ids: List<Int>,
    val id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
//    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
//    val video: Boolean,
//    val vote_average: Float,
//    val vote_count: Float
)
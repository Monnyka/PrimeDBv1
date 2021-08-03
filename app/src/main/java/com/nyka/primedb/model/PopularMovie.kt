package com.nyka.primedb.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "popularMovie"
)
data class PopularMovie(
    @PrimaryKey(autoGenerate = true)
    val id : Int?,
    val page: Int,
    val result: List<PopularResult>,
    val total_pages: Int,
    val total_results: Int
)
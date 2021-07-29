package com.nyka.primedb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nyka.primedb.db.Converters

@Entity(
    tableName = "trendingMovie"
)
data class TrendingMovie(

    @PrimaryKey(autoGenerate = true)
    val id : Int?,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
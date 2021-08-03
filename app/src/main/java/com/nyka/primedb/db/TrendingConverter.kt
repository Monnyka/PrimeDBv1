package com.nyka.primedb.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nyka.primedb.model.PopularResult
import com.nyka.primedb.model.TrendingMovieResult


class TrendingConverter {
    @TypeConverter
    fun fromString(value: String): List<TrendingMovieResult> {
        val listType = object : TypeToken<List<TrendingMovieResult>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListList(lists: List<TrendingMovieResult>): String {
        val gson = Gson()
        return gson.toJson(lists)
    }
}
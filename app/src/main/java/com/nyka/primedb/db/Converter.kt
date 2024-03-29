package com.nyka.primedb.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nyka.primedb.model.PopularResult
import com.nyka.primedb.model.TrendingMovieResult


class Converters {
    @TypeConverter
    fun fromString(value: String): List<PopularResult> {
        val listType = object : TypeToken<List<PopularResult>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListList(lists: List<PopularResult>): String {
        val gson = Gson()
        return gson.toJson(lists)
    }
}
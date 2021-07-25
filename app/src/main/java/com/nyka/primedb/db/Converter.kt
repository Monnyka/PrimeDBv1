package com.nyka.primedb.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nyka.primedb.model.Result


class Converters {
    @TypeConverter
    fun fromString(value: String): List<Result> {
        val listType = object : TypeToken<List<Result>>() {}.type
        return Gson().fromJson(value, listType)

    }

    @TypeConverter
    fun fromListList(list: List<Result>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}
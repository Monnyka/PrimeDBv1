package com.nyka.primedb.repository

import com.nyka.primedb.api.RetrofitInstance
import com.nyka.primedb.db.MovieDatabase

class MovieRepository(
    val db: MovieDatabase
    ){
    suspend fun getTrendingMovie() =
        RetrofitInstance.api.getTrendingMovie()
}
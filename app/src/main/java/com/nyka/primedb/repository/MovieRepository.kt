package com.nyka.primedb.repository

import com.nyka.primedb.api.RetrofitInstance
import com.nyka.primedb.db.MovieDatabase
import com.nyka.primedb.model.PopularMovie
import com.nyka.primedb.model.TrendingMovie

class MovieRepository(
    private val db: MovieDatabase
    ){
    suspend fun getTrendingMovie() = RetrofitInstance.api.getTrendingMovie()
    suspend fun upsertTrending(trendingMovie: TrendingMovie) = db.getMovieDao().upsertTrendingMovie(trendingMovie)
    fun getSavedTrendingMovie() = db.getMovieDao().getAllTrendingMovie()

    suspend fun getPopularMovie() = RetrofitInstance.api.getPopularMovie()
    suspend fun upsertPopular(popularMovie: PopularMovie) = db.getMovieDao().upsertPopularMovie(popularMovie)
    fun getSavedPopularMovie() = db.getMovieDao().getAllPopular()
}
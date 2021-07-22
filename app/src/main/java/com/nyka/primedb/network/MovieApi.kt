package com.nyka.primedb.network

import com.nyka.primedb.model.MovieDetail
import com.nyka.primedb.model.TrendingMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/{version}/movie/popular")
    suspend fun getMovie(
        @Path(value = "version", encoded = true) version : Int,
        @Query("api_key") apiKey: String): Response<TrendingMovie>

    @GET("/{version}/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path(value = "version", encoded = true) version : Int,
        @Path(value = "movie_id", encoded = false) movie_id: String,
        @Query("api_key") apiKey: String
    ) : Response<MovieDetail>
}
package com.nyka.primedb.api

import com.nyka.primedb.model.MovieDetail
import com.nyka.primedb.model.PopularMovie
import com.nyka.primedb.model.TrendingMovie
import com.nyka.primedb.utils.Constants.Companion.api_key
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/{version}/movie/popular")
    suspend fun getPopularMovie(
        @Path(value = "version", encoded = true) version : Int = 3,
        @Query("api_key") apiKey: String = api_key): Response<PopularMovie>

    @GET("/{version}/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path(value = "version", encoded = true) version : Int,
        @Path(value = "movie_id", encoded = false) movie_id: String,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") append_to_response: String = "credits") : Response<MovieDetail>

    @GET("{version}/trending/movie/day")
    suspend fun getTrendingMovie(
        @Path(value = "version", encoded = true) version : Int = 3,
        @Query("api_key") apiKey: String = api_key): Response<TrendingMovie>

}
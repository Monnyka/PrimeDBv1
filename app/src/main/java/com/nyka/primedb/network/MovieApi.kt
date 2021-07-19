package com.nyka.primedb.network

import com.nyka.primedb.model.TrendingMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/movie/popular")
    suspend fun getMovie(@Query("api_key") api_key: String) : Response<List<TrendingMovie>>

}
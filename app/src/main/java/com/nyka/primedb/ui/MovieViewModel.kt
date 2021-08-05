package com.nyka.primedb.ui

import android.app.Application
import android.content.Context
import android.graphics.Movie
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.util.Log
import androidx.lifecycle.*
import com.nyka.primedb.MovieApplication
import com.nyka.primedb.model.PopularMovie
import com.nyka.primedb.model.TrendingMovie
import com.nyka.primedb.repository.MovieRepository
import com.nyka.primedb.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MovieViewModel(
    app: MovieApplication,
    private val movieRepository: MovieRepository
) : AndroidViewModel(app) {

    val popularMovie : MutableLiveData<Resource<PopularMovie>> = MutableLiveData()
    val trendingMovie: MutableLiveData<Resource<TrendingMovie>> = MutableLiveData()

    init {
//        getPopularMovie()
        getTrendingMovie()
    }

    private fun getTrendingMovie() = viewModelScope.launch {
        safeTrendingMovieCall()
    }

//    private fun getPopularMovie()= viewModelScope.launch {
//        popularMovie.postValue(Resource.Loading())
//        val response = movieRepository.getPopularMovie()
//        popularMovie.postValue(handleGetPopularMovieResponse(response))
//    }

    private fun handleGetTrendingMovieResponse(response: Response<TrendingMovie>): Resource<TrendingMovie>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleGetPopularMovieResponse(response: Response<PopularMovie>) : Resource<PopularMovie> {
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

//    fun savePopularMovie(popularMovie: PopularMovie) = viewModelScope.launch {
//        movieRepository.upsertPopular(popularMovie)
//    }
//
//    fun getAllSavedPopularMovie() = movieRepository.getSavedPopularMovie()
//
    fun getAllSavedTrendingMovie() = movieRepository.getSavedTrendingMovie()

    fun saveTrendingMovie(trendingMovie: TrendingMovie) = viewModelScope.launch {
        movieRepository.upsertTrending(trendingMovie)
    }

    private suspend fun safeTrendingMovieCall(){
        trendingMovie.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()){
                val response = movieRepository.getTrendingMovie()
                trendingMovie.postValue(handleGetTrendingMovieResponse(response))
            }else{
                trendingMovie.postValue(Resource.Error("There is no internet connection."))
            }
        }catch (t: Throwable){
            when(t){
                is IOException -> trendingMovie.postValue(Resource.Error("Network failure"))
                else -> trendingMovie.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<MovieApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}


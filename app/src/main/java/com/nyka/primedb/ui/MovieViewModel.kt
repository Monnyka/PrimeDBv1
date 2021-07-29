package com.nyka.primedb.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyka.primedb.db.MovieDatabase
import com.nyka.primedb.model.TrendingMovie
import com.nyka.primedb.repository.MovieRepository
import com.nyka.primedb.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(
    val movieRepository: MovieRepository
) : ViewModel() {

    val trendingMovie : MutableLiveData<Resource<TrendingMovie>> = MutableLiveData()

    init {
        getTrendingMovie()
    }

    fun getTrendingMovie()= viewModelScope.launch {
        trendingMovie.postValue(Resource.Loading())
        val response = movieRepository.getTrendingMovie()
        trendingMovie.postValue(handleTrendingMovieResponse(response))
    }

    private fun handleTrendingMovieResponse(response: Response<TrendingMovie>) : Resource<TrendingMovie> {
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}


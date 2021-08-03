package com.nyka.primedb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyka.primedb.model.PopularMovie
import com.nyka.primedb.model.TrendingMovie
import com.nyka.primedb.repository.MovieRepository
import com.nyka.primedb.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val popularMovie : MutableLiveData<Resource<PopularMovie>> = MutableLiveData()
    val trendingMovie: MutableLiveData<Resource<TrendingMovie>> = MutableLiveData()

    init {
        getPopularMovie()
        getTrendingMovie()
    }

    private fun getTrendingMovie() = viewModelScope.launch {
        trendingMovie.postValue(Resource.Loading())
        val response = movieRepository.getTrendingMovie()
        trendingMovie.postValue(handleGetTrendingMovieResponse(response))
    }

    private fun getPopularMovie()= viewModelScope.launch {
        popularMovie.postValue(Resource.Loading())
        val response = movieRepository.getPopularMovie()
        popularMovie.postValue(handleGetPopularMovieResponse(response))
    }

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

    fun savePopularMovie(popularMovie: PopularMovie) = viewModelScope.launch {
        movieRepository.upsertPopular(popularMovie)
    }

    fun getAllSavedPopularMovie() = movieRepository.getSavedPopularMovie()

    fun saveTrendingMovie(trendingMovie: TrendingMovie) = viewModelScope.launch {
        movieRepository.upsertTrending(trendingMovie)
    }

}


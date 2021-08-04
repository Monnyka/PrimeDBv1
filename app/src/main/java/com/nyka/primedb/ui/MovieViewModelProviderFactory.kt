package com.nyka.primedb.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nyka.primedb.MovieApplication
import com.nyka.primedb.repository.MovieRepository

class MovieViewModelProviderFactory(
    val app: MovieApplication,
    private val movieRepository: MovieRepository
) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(app, movieRepository) as T
    }
}

package com.nyka.primedb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nyka.primedb.db.MovieDatabase
import com.nyka.primedb.repository.MovieRepository

class MovieViewModelProviderFactory(
    val movieRepository: MovieRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(movieRepository) as T
    }
}

package com.nyka.primedb.ui

import androidx.lifecycle.ViewModel
import com.nyka.primedb.db.MovieDatabase

class MovieViewModel(
    val movieRepository: MovieDatabase
) : ViewModel() {


}
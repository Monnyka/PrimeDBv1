package com.nyka.primedb.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nyka.primedb.MovieDetailActivity
import com.nyka.primedb.R
import com.nyka.primedb.databinding.FragmentMovieBinding

class MovieFragment:Fragment(R.layout.fragment_movie) {
    private var movieFragmentBinding: FragmentMovieBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMovieBinding.bind(view)
        movieFragmentBinding = binding

        binding.constraintLayout3.setOnClickListener(){
            Intent(activity, MovieDetailActivity::class.java).also {
                startActivity(it)
            }
        }
    }

}
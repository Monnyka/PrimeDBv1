package com.nyka.primedb.ui.fragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nyka.primedb.ui.MovieDetailActivity
import com.nyka.primedb.R
import com.nyka.primedb.adapter.MovieAdapter
import com.nyka.primedb.adapter.TrendingMovieAdapter
import com.nyka.primedb.databinding.FragmentMovieBinding
import com.nyka.primedb.ui.MainActivity
import com.nyka.primedb.ui.MovieViewModel
import com.nyka.primedb.utils.Resource

class MovieFragment:Fragment(R.layout.fragment_movie) {
    private var movieFragmentBinding: FragmentMovieBinding? = null
    lateinit var viewModel: MovieViewModel
    lateinit var popularAdapter: MovieAdapter
    lateinit var trendingAdapter : TrendingMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMovieBinding.bind(view)
        movieFragmentBinding = binding
        viewModel = (activity as MainActivity).viewModel

//        viewModel.getAllSavedPopularMovie().observe(viewLifecycleOwner, {
//            if(it.isNotEmpty()) {
//                popularAdapter.differ.submitList(it.first().popularResults)
//                Snackbar.make(view, "Get saved movie...", Snackbar.LENGTH_SHORT).show()
//            }
//        })

        trendingAdapter = TrendingMovieAdapter()
        binding.rvTrendingMovie?.apply {
            adapter = trendingAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.trendingMovie.observe(viewLifecycleOwner, Observer{ response ->
            when(response){
                is Resource.Success ->{
                    response.data?.let {
                        trendingAdapter.differTrending.submitList(it.results)
                        //viewModel.saveTrendingMovie(it)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occurred: $message")
                        Snackbar.make(view, "Error", Snackbar.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {

                }
            }
        })

        popularAdapter = MovieAdapter()
        binding.rvPopularMovie.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.popularMovie.observe(viewLifecycleOwner, { responses ->
            when(responses) {
                is Resource.Success -> {
                    responses.data?.let {
                        popularAdapter.differ.submitList(it.result)
                        //viewModel.savePopularMovie(it)
                        Snackbar.make(view, "Successful get the movie...", Snackbar.LENGTH_SHORT).show()
                    }
                }
                is Resource.Error -> {
                    responses.message?.let { message ->
                        Log.e(TAG, "An error occurred: $message")
                        Snackbar.make(view, "Error", Snackbar.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading ->{

                }
            }
        })

        binding.constraintLayout3.setOnClickListener {
            Intent(activity, MovieDetailActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}


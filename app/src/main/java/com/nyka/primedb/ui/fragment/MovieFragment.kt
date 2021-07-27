package com.nyka.primedb.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nyka.primedb.ui.MovieDetailActivity
import com.nyka.primedb.R
import com.nyka.primedb.adapter.MovieAdapter
import com.nyka.primedb.databinding.FragmentMovieBinding
import com.nyka.primedb.model.TrendingMovie
import com.nyka.primedb.ui.MainActivity
import com.nyka.primedb.ui.MovieViewModel
import com.nyka.primedb.utils.Resource

class MovieFragment:Fragment(R.layout.fragment_movie) {
    private var movieFragmentBinding: FragmentMovieBinding? = null
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

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

        movieAdapter = MovieAdapter()
        binding.rvTrendingMovie.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.trendingMovie.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let {
                        movieAdapter.differ.submitList(mutableListOf(it))
                        println("$it fffffffffffffffffffff")
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e("dddddddddd", "An error occured: $message")
                    }

                }
            }
        })


//        val trendingMovie = mutableListOf<TrendingMovie>(
//            TrendingMovie("1","Black Widow","2021","https://image.tmdb.org/t/p/original/kAY8htLwxylV79IhkVilbiDYybQ.jpg"),
//            TrendingMovie("4","Avengers: End Game","2061","https://www.themoviedb.org/t/p/original/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg")
//        )

//        lifecycleScope.launchWhenCreated {
//            val response = try {
//                RetrofitInstance.api.getMovie(3, api_key)
//
//                }catch (e: Exception){
//                    Log.d(TAG, "IOException: You might not have internet connection $e")
//                    return@launchWhenCreated
//                }catch (e: HttpException){
//                Log.d(TAG, "HTTPException: Unexpected Response" )
//                return@launchWhenCreated
//                }
//
//            if(response.isSuccessful && response.body()!=null){
//                binding.rvTrendingMovie.apply {
//                    adapter = TrendingMovieAdapter(response.body()!!)
//                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//                }
//
//            }else{
//                Log.e(TAG, "Response not successful")
//            }
//        }
        binding.constraintLayout3.setOnClickListener() {
            Intent(activity, MovieDetailActivity::class.java).also {
                startActivity(it)
            }
        }


    }
}

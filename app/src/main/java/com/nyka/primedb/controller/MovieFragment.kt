package com.nyka.primedb.controller

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nyka.primedb.MovieDetailActivity
import com.nyka.primedb.R
import com.nyka.primedb.adaper.TrendingMovieAdapter
import com.nyka.primedb.databinding.FragmentMovieBinding
import com.nyka.primedb.model.TrendingMovie
import com.nyka.primedb.network.RetrofitInstance
import com.nyka.primedb.utils.Constants.Companion.api_key
import retrofit2.HttpException
import java.lang.Exception

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

//        val trendingMovie = mutableListOf<TrendingMovie>(
//            TrendingMovie("1","Black Widow","2021","https://image.tmdb.org/t/p/original/kAY8htLwxylV79IhkVilbiDYybQ.jpg"),
//            TrendingMovie("4","Avengers: End Game","2061","https://www.themoviedb.org/t/p/original/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg")
//        )

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getMovie(3, api_key)

                }catch (e: Exception){
                    Log.d(TAG, "IOException: You might not have internet connection $e")
                    return@launchWhenCreated
                }catch (e: HttpException){
                Log.d(TAG, "HTTPException: Unexpected Response" )
                return@launchWhenCreated
                }

            if(response.isSuccessful && response.body()!=null){
                binding.rvTrendingMovie.apply {
                    adapter = TrendingMovieAdapter(response.body()!!)
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                }

            }else{
                Log.e(TAG, "Response not successful")
            }
        }

    }
}
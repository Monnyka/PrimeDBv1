package com.nyka.primedb.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.nyka.primedb.ui.MovieDetailActivity
import com.nyka.primedb.R
import com.nyka.primedb.databinding.FragmentMovieBinding
import com.nyka.primedb.ui.MainActivity
import com.nyka.primedb.ui.MovieViewModel

class MovieFragment:Fragment(R.layout.fragment_movie) {
    private var movieFragmentBinding: FragmentMovieBinding? = null
    private lateinit var viewModel : MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_movie,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMovieBinding.bind(view)
        movieFragmentBinding = binding
        viewModel = (activity as MainActivity).viewModel

        binding.constraintLayout3.setOnClickListener(){
            Intent(activity, MovieDetailActivity::class.java).also {
                startActivity(it)
            }
        }

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

    }
}
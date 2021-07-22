package com.nyka.primedb

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.nyka.primedb.databinding.ActivityMovieDetailBinding
import com.nyka.primedb.model.MovieDetail
import com.nyka.primedb.model.TrendingMovie
import com.nyka.primedb.network.RetrofitInstance
import com.nyka.primedb.utils.Constants.Companion.api_key
import com.nyka.primedb.utils.Constants.Companion.posterPath
import okhttp3.internal.wait
import retrofit2.HttpException
import java.lang.Exception

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieId = intent.getStringExtra("movie_id")
        if(movieId!=null) {
            lifecycleScope.launchWhenCreated {
                val response = try {
                    RetrofitInstance.api.getMovieDetail(3, movieId, api_key)

                } catch (e: Exception) {
                    Log.d(
                        ContentValues.TAG,
                        "IOException: You might not have internet connection $e"
                    )
                    return@launchWhenCreated
                } catch (e: HttpException) {
                    Log.d(ContentValues.TAG, "HTTPException: Unexpected Response $e")
                    return@launchWhenCreated
                }
                if (response.isSuccessful && response.body() != null) {
                    val movieDetail: MovieDetail = response.body()!!
                    binding.apply {
                        tvMovieTitle.text = movieDetail.title
                        tvTagLine.text = movieDetail.tagline
                        tvReleaseDate.text = movieDetail.release_date
                        val duration = "${movieDetail.runtime} Minute"
                        tvDuration.text = duration
                        tvLanguage.text = movieDetail.spoken_languages[0].english_name
                        tvRating.text = movieDetail.vote_average.toString()
                        tvBudget.text = movieDetail.budget.toString()
                        tvAvenue.text = movieDetail.revenue.toString()
                        tvSynopsis.text = movieDetail.overview
                        val imagePoster = "${posterPath}${movieDetail.poster_path}"
                        val imageBackDrop = "${posterPath}${movieDetail.backdrop_path}"
                        Glide.with(applicationContext).load(imagePoster).into(binding.imPoster)
                        Glide.with(applicationContext).load(imageBackDrop).into(binding.ivBackDrop)
                    }

                } else {
                    println("Request not successful")
                }
            }
        }else{
            print("movie not found")
        }
    }

}
package com.nyka.primedb

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nyka.primedb.adaper.ProductionAdapter
import com.nyka.primedb.databinding.ActivityMovieDetailBinding
import com.nyka.primedb.model.MovieDetail
import com.nyka.primedb.model.TrendingMovie
import com.nyka.primedb.network.RetrofitInstance
import com.nyka.primedb.utils.Constants.Companion.api_key
import com.nyka.primedb.utils.Constants.Companion.posterPath
import kotlinx.coroutines.joinAll
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
                        val tagLing = movieDetail.tagline
                        if(tagLing !=""){
                            tvTagLine.text = tagLing
                        }else tvTagLine.visibility = View.GONE

                        tvReleaseDate.text = movieDetail.release_date
                        val duration = "${movieDetail.runtime} Minute"
                        tvDuration.text = duration
                        tvLanguage.text = movieDetail.spoken_languages[0].english_name
                        tvRating.text = movieDetail.vote_average.toString()
                        tvBudget.text = movieDetail.budget.toString()
                        tvAvenue.text = movieDetail.revenue.toString()
                        tvSynopsis.text = movieDetail.overview
                        if (tvGenre != null) {
                            tvGenre.text = movieDetail.genres[0].name
                        }
                    val imagePoster = "${posterPath}${movieDetail.poster_path}"
                        val imageBackDrop = "${posterPath}${movieDetail.backdrop_path}"
                        Glide.with(applicationContext).load(imagePoster).into(binding.imPoster)
                        Glide.with(applicationContext).load(imageBackDrop).into(binding.ivBackDrop)
                        if (svMain != null && pbLoading !=null) {
                            svMain.visibility = View.VISIBLE
                            pbLoading.visibility = View.GONE
                        }
                    }

                    binding.rvProduction?.apply {
                        adapter = ProductionAdapter(response.body()!!)
                        layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
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
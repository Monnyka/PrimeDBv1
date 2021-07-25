package com.nyka.primedb.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nyka.primedb.adapter.CastAdapter
import com.nyka.primedb.adapter.ProductionAdapter
import com.nyka.primedb.databinding.ActivityMovieDetailBinding
import com.nyka.primedb.data.model.MovieDetail
import com.nyka.primedb.api.RetrofitInstance
import com.nyka.primedb.utils.Constants.Companion.api_key
import com.nyka.primedb.utils.Constants.Companion.posterPath
import retrofit2.HttpException
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

class MovieDetailActivity : AppCompatActivity(){
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

                        tvReleaseDate.text = dateFormatting(movieDetail.release_date)
                        val duration = "${movieDetail.runtime} Minute"
                        tvDuration.text = duration
                        tvLanguage.text = movieDetail.spoken_languages[0].english_name
                        tvRating.text = movieDetail.vote_average.toString()
                        tvBudget.text = prettyCount(movieDetail.budget)
                        tvAvenue.text = prettyCount(movieDetail.revenue)
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
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    }

                    binding.rvCast?.apply {
                        adapter = CastAdapter(response.body()!!)
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    }


                } else {
                    println("Request not successful")
                }
            }
        }else{
            print("movie not found")
        }
    }

    private fun prettyCount(number: Number): String? {
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val numValue = number.toLong()
        val value = floor(log10(numValue.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
//            DecimalFormat("#0.00").format(
            DecimalFormat("#0").format(
                numValue / 10.0.pow((base * 3).toDouble())
            ) + suffix[base]
        } else {
            DecimalFormat().format(numValue)
        }
    }

    private fun dateFormatting(date: String): String {
        val pattern = "dd MMMM, yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern)
        return simpleDateFormat.format(Date())
    }

}
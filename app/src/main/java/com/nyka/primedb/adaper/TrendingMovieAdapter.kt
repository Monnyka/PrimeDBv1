package com.nyka.primedb.adaper

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nyka.primedb.MovieDetailActivity
import com.nyka.primedb.R
import com.nyka.primedb.model.TrendingMovie

class TrendingMovieAdapter(private var trendingMovie: TrendingMovie) : RecyclerView.Adapter<TrendingMovieAdapter.TrendingMovieHolder>(){

    inner class TrendingMovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingMovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return TrendingMovieHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingMovieHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tv_title).text = trendingMovie.results[position].title
        holder.itemView.findViewById<TextView>(R.id.tv_year).text = trendingMovie.results[position].release_date
        val imageUrl ="https://image.tmdb.org/t/p/w500${trendingMovie.results[position].poster_path}"
        Glide.with(holder.itemView.context).load(imageUrl).into(holder.itemView.findViewById(R.id.iv_poster))

        holder.itemView.setOnClickListener(){
            Intent(holder.itemView.context, MovieDetailActivity::class.java).also {
                holder.itemView.context.startActivities(arrayOf(it))
            }
        }
    }

    override fun getItemCount(): Int {
        return trendingMovie.results.size
    }
}
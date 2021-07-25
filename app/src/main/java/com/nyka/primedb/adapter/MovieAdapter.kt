package com.nyka.primedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nyka.primedb.R
import com.nyka.primedb.data.model.TrendingMovie
import com.nyka.primedb.utils.Constants.Companion.posterPath

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<TrendingMovie>(){
         override fun areItemsTheSame(oldItem: TrendingMovie, newItem: TrendingMovie): Boolean {
            return oldItem.primary_id == newItem.primary_id
        }

        override fun areContentsTheSame(oldItem: TrendingMovie, newItem: TrendingMovie): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.itemView.apply {
            val posterImage = posterPath+movie.results[position].poster_path
            Glide.with(this).load(posterImage).into(findViewById(R.id.iv_poster))
        }
        holder.itemView.findViewById<TextView>(R.id.tv_title).text = movie.results[position].title
        holder.itemView.findViewById<TextView>(R.id.tv_year).text = movie.results[position].title
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
package com.nyka.primedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nyka.primedb.R
import com.nyka.primedb.model.MovieDetail
import com.nyka.primedb.utils.Constants.Companion.posterPath

class CastAdapter(private var movieDetail: MovieDetail) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    inner class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvName).text = movieDetail.credits.cast[position].name
        holder.itemView.findViewById<TextView>(R.id.tvCharacter).text = movieDetail.credits.cast[position].character
        val imageCast = posterPath + movieDetail.credits.cast[position].profile_path
        Glide.with(holder.itemView.context).load(imageCast).into(holder.itemView.findViewById(R.id.ivProfile))
    }

    override fun getItemCount(): Int {
        return movieDetail.credits.cast.size
    }


}
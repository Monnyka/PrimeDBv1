package com.nyka.primedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nyka.primedb.R
import com.nyka.primedb.data.model.MovieDetail
import com.nyka.primedb.utils.Constants.Companion.posterPath

class ProductionAdapter(private var production: MovieDetail): RecyclerView.Adapter<ProductionAdapter.ProductionViewHolder>() {

    inner class ProductionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_production, parent, false)
        return ProductionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductionViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvProduction).text = production.production_companies[position].name
        val imageProduction = posterPath + production.production_companies[position].logo_path
        Glide.with(holder.itemView.context).load(imageProduction).into(holder.itemView.findViewById(R.id.ivProduction))
    }

    override fun getItemCount(): Int {
        return production.production_companies.size
    }

}
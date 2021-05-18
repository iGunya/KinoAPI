package com.example.kinoapi.start

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinoapi.databinding.ItemPosterFilmBinding

class TopFilmViewHolder (
    binding: ItemPosterFilmBinding,
    private val onFilmClick: (filmId: Int)->Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val imageView = binding.cview



    fun setImageData(imageData: Film){
        Glide.with(imageView).load(imageData.posterUrlPreview).into(imageView)
        imageView.setOnClickListener { onFilmClick(imageData.filmId) }
    }

}
package com.example.kinoapi.start

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kinoapi.databinding.ItemPosterFilmBinding

class TopFrameAdapter constructor(private val onFilmClick: (filmId: Int)->Unit) : RecyclerView.Adapter<TopFilmViewHolder>() {



    private val items = mutableListOf<Film>()

    fun setItems(frames: List<Film>){
        items.clear()
        items.addAll(frames)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopFilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPosterFilmBinding.inflate(layoutInflater)
        return TopFilmViewHolder(binding, onFilmClick)
    }

    override fun onBindViewHolder(holder: TopFilmViewHolder, position: Int) {
        holder.setImageData(items[position])
    }

    override fun getItemCount(): Int =items.size


}
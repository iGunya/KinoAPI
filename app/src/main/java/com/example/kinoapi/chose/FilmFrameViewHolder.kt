package com.example.kinoapi.chose

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinoapi.databinding.ItemFilmFrameBinding

/**
 * Класс для отображения элемента списка кадров фильма
 */
class FilmFrameViewHolder(
    binding: ItemFilmFrameBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val imageView = binding.root

    /**
     * Загрузить изображение по ссылке [imageUrl] и отобразить его в текущем элементе списка
     */
    fun setImageUrl(imageUrl: String) {
        Glide.with(imageView).load(imageUrl).into(imageView)
    }
}
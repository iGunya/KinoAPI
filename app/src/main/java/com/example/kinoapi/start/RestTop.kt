package com.example.kinoapi.start

import com.google.gson.annotations.SerializedName

data class RestTop (
    @SerializedName("films") val top: List<Film>,
)

data class Film(
    val filmId: Int,
    val posterUrlPreview: String,
)
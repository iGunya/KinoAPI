package com.example.kinoapi.chose

import com.google.gson.annotations.SerializedName

data class RestFilmPoster(
    @SerializedName("frames") val frames: ArrayList<Frame>,
)


data class Frame (
    val image: String,
    val preview: String,
)
package com.example.kinoapi.chose

import com.google.gson.annotations.SerializedName

data class RestFilm(
    @SerializedName("filmId") val id: Long,
    @SerializedName("nameRu") val nameRus: String,
    @SerializedName("nameEn") val nameEng: String,
    @SerializedName("slogan") val slogan: String,
    @SerializedName("description") val disctiption: String,
    @SerializedName("year") val year: String,
    @SerializedName("filmLength") val filmLength: String,
    @SerializedName("posterUrlPreview") val Preview: String,
    @SerializedName("genres") val genres: ArrayList<Genre>,
)

data class Genre (
    val genre: String,
)
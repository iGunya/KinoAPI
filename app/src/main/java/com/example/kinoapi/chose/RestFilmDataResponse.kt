package com.example.kinoapi.chose

import com.google.gson.annotations.SerializedName

data class  RestFilmDataResponse(
    @SerializedName("data") val film: RestFilm,
)
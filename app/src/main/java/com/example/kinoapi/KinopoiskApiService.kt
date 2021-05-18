package com.example.kinoapi

import com.example.kinoapi.chose.RestFilmDataResponse
import com.example.kinoapi.chose.RestFilmPoster
import com.example.kinoapi.start.RestTop
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface KinopoiskApiService {
    /** Получение данных о фильме по идентификатору [filmId] */
    @GET("/api/v2.1/films/{id}")
    fun getFilmData(@Path("id") filmId: Int): Call<RestFilmDataResponse>

    /** Получение ссылок на кадры/постеры фильма по идентификатору [filmId] */
    @GET("/api/v2.1/films/{id}/frames")
    fun getFilmPoster(@Path("id") filmId: Int): Call<RestFilmPoster>

    /** Получение списка фильмов из раздела ТОПов (3 TOPа) */
    @GET("/api/v2.2/films/top?&page=1")
    fun getTop(@Query("type") filmId: String): Call<RestTop>
}
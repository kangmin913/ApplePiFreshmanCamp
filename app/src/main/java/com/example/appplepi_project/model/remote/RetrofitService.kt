package com.example.appplepi_project.model.remote

import com.example.appplepi_project.model.data.WeatherRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("/{day}")
    fun getWeather(@Path("day") day : Int): Call<WeatherRes>
}
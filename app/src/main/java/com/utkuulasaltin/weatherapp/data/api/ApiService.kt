package com.utkuulasaltin.weatherapp.data.api

import com.utkuulasaltin.weatherapp.data.model.OneCall
import com.utkuulasaltin.weatherapp.data.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET(Constants.ONECALL)
    fun getOneCallWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String,
        @Query("exclude") exclude: String,
        @Query("units") units: String
    ): Call<OneCall>
}
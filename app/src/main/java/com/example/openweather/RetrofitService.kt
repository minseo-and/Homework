package com.example.openweather

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("data/2.5/weather?/")
    fun getWeather(@Query ("lat") lat : Double, @Query("lon") lon : Double, @Query("appid") appid:String)
    : Call<WeatherData>


}
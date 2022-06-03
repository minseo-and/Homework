package com.example.openweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var et_lat = findViewById<EditText>(R.id.et_lat)
        var et_lon = findViewById<EditText>(R.id.et_lon)
        var tv_result = findViewById<TextView>(R.id.tv_result)
        var btn_serch = findViewById<Button>(R.id.btn_search)

        btn_serch.setOnClickListener {

            Toast.makeText(applicationContext, "없", Toast.LENGTH_SHORT).show()
            val service = ApiClient.getInstance().create(RetrofitService::class.java)
            val call : Call<WeatherData> = service.getWeather(36.3504119, 127.3845475, "62f35648354d42c13bee492574499f4d")
            call.enqueue(object : Callback<WeatherData> {
                override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                    if (response.isSuccessful){

                        val weather = response.body()
                        val main : Main = weather!!.main
                        val temp : Double = main.temp
                        val tempmax : Double = main.temp_max
                        val tempmin : Double = main.temp_min
                        val temperature : Double = (temp-273.15)
                        val temperaturemax : Double = (tempmax-273.15)
                        val temperaturemin : Double = (tempmin-273.15)
                        val tv : String = (temperature.toString()+ "\n" + temperaturemax.toString()+ "\n" + temperaturemin.toString())
                        tv_result.text = tv
                        Log.d("msg", response.toString())
                        Toast.makeText(applicationContext, "있", Toast.LENGTH_SHORT).show()

                    } else {
                        Log.d("msg", response.toString())
                        Toast.makeText(applicationContext, response.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    Toast.makeText(applicationContext, "없음", Toast.LENGTH_SHORT).show()
                }

            })
        }


    }


}


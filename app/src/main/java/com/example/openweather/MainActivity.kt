package com.example.openweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tv_result = findViewById<TextView>(R.id.tv_result)
        var btn_serch = findViewById<Button>(R.id.btn_search)

        btn_serch.setOnClickListener {
            val service = ApiClient.getInstance().create(RetrofitService::class.java)
            val call : Call<WeatherData> = service.getWeather(36.3504119, 127.3845475, "62f35648354d42c13bee492574499f4d")
            call.enqueue(object : Callback<WeatherData> {
                override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                    if (response.isSuccessful){

                        val weather = response.body()
                        val main : Main = weather!!.main
                        val temp : Double = main.temp
                        val temperature : Double = (temp-273.15)
                        val tv : String = ("대전은 현재 "+temperature.toInt().toString()+"C")
                        tv_result.text = tv
                        Log.d("msg", response.toString())

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


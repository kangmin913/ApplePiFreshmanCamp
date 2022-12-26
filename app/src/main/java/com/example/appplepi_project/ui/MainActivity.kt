package com.example.appplepi_project.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.appplepi_project.R
import com.example.appplepi_project.databinding.ActivityMainBinding
import com.example.appplepi_project.model.data.WeatherRes
import com.example.appplepi_project.model.remote.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var yesterdayData: WeatherRes
    private lateinit var todayData: WeatherRes
    private lateinit var tomorrowData: WeatherRes

    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val retrofit = Retrofit
            .Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitService::class.java)

        val weatherList = ArrayList<WeatherRes>()
        service.getWeather(-1).enqueue(object : Callback<WeatherRes> {



            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<WeatherRes>, response: Response<WeatherRes>) {

                if (response.isSuccessful) {
                    val result: WeatherRes? = response.body()
                    yesterdayData = WeatherRes(
                        result!!.humidity,
                        result.PM10,
                        result.PM2_5,
                        result.rain,
                        result.temp,
                        result.newsTitle,
                        result.newsContent,
                        result.dt
                    )
                    weatherList.add(yesterdayData)
                    binding.vpSample.adapter = ViewPagerAdapter(weatherList)
                    Log.d("GetWeatherData", result.toString())
                } else {
                    Log.d("GetWeatherData", "onResponse 실패")
                }
            }

            override fun onFailure(call: Call<WeatherRes>, t: Throwable) {
                Log.d("GetWeatherData", "onFailure 에러 : " + t.message.toString())
            }

        })
        service.getWeather(1)?.enqueue(object : Callback<WeatherRes> {
            override fun onResponse(call: Call<WeatherRes>, response: Response<WeatherRes>) {
                if (response.isSuccessful) {
                    val result: WeatherRes? = response.body()
                    tomorrowData = WeatherRes(
                        result!!.humidity,
                        result.PM10,
                        result.PM2_5,
                        result.rain,
                        result.temp,
                        result.newsTitle,
                        result.newsContent,
                        result.dt
                    )
                    weatherList.add(tomorrowData)
                    binding.vpSample.adapter = ViewPagerAdapter(weatherList)
                    Log.d("GetWeatherData", result.toString())
                } else {
                    Log.d("GetWeatherData", "onResponse 실패")
                }
            }

            override fun onFailure(call: Call<WeatherRes>, t: Throwable) {
                Log.d("GetWeatherData", "onFailure 에러 : " + t.message.toString())
            }

        })
        service.getWeather(0)?.enqueue(object : Callback<WeatherRes> {
            override fun onResponse(call: Call<WeatherRes>, response: Response<WeatherRes>) {
                if (response.isSuccessful) {
                    val result: WeatherRes? = response.body()
                    todayData = WeatherRes(
                        result!!.humidity,
                        result.PM10,
                        result.PM2_5,
                        result.rain,
                        result.temp,
                        result.newsTitle,
                        result.newsContent,
                        result.dt
                    )
                    weatherList.add(todayData)
                    binding.vpSample.adapter = ViewPagerAdapter(weatherList)
                    Log.d("GetWeatherData", result.toString())
                } else {
                    Log.d("GetWeatherData", "onResponse 실패" + response.code())
                }
            }

            override fun onFailure(call: Call<WeatherRes>, t: Throwable) {
                Log.d("GetWeatherData", "onFailure 에러 : " + t.message.toString())
            }

        })
    }


    companion object {
        const val BaseUrl = "http://18.176.54.100"
    }
}
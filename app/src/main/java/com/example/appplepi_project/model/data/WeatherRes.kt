package com.example.appplepi_project.model.data

import com.google.gson.annotations.SerializedName

data class WeatherRes (
        @SerializedName("humidity")
        val humidity : String, // 습도
        @SerializedName("PM10")
        val PM10 : String, // 미세먼지
        @SerializedName("PM2_5")
        val PM2_5 : String, // 초 미세먼지
        @SerializedName("rain")
        val rain : String, // 강수 확률
        @SerializedName("temp")
        val temp : String, // 기온
        @SerializedName("newsTitle")
        val newsTitle : String, // 뉴스제목
        @SerializedName("newsContent")
        val newsContent : String, // 뉴스내용
        @SerializedName("dt")
        val dt : Int // 날짜
)
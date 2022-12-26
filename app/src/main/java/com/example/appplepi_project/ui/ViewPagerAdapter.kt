package com.example.appplepi_project.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.appplepi_project.R
import com.example.appplepi_project.model.data.WeatherRes
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ViewPagerAdapter(private val list: ArrayList<WeatherRes>) : PagerAdapter() {
    @SuppressLint("SetTextI18n")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.layout_weather, container, false)

        //findViewById 로 변수에 저장
        val humidity = view.findViewById<TextView>(R.id.tv_main_humidityvalue) // 습도
        val PM10 = view.findViewById<TextView>(R.id.tv_main_PM10value) // 미세먼지
        val PM2_5 = view.findViewById<TextView>(R.id.tv_main_PM2_5value) // 자외선
        val rain = view.findViewById<TextView>(R.id.tv_main_rainvalue) // 강수확률
        val temp = view.findViewById<TextView>(R.id.tv_main_tempvalue) // 기온
        val newsTitle = view.findViewById<TextView>(R.id.tv_main_newsTitle) // 뉴스 제목
        val newsContent = view.findViewById<TextView>(R.id.tv_main_newsContent) // 뉴스 내용

        val dateText = view.findViewById<TextView>(R.id.tv_main_weather) // 오늘/내일/어제 날씨
        val dateNum = view.findViewById<TextView>(R.id.tv_main_date) // 날짜
        val message = view.findViewById<ImageView>(R.id.message)//날씨 상태 메세지
        val weather_word = view.findViewById<ImageView>(R.id.weather_word)//날씨 상태 문구
        val weather_image = view.findViewById<ImageView>(R.id.weather_image)//날씨 상태 이미지





        val help = list[position].PM10.toDouble()
        if(help>=35) {
            message.setImageResource(R.drawable.message_bad)
            weather_image.setImageResource(R.drawable.weather_image_bad)
            weather_word.setImageResource(R.drawable.weather_word_bad)
        }

        else if(help<35){
            message.setImageResource(R.drawable.message_good)
            weather_image.setImageResource(R.drawable.weather_image_good)
            weather_word.setImageResource(R.drawable.weather_word_good)
        }
        humidity.text = list[position].humidity + "%"
        PM10.text = list[position].PM10 + "㎍/m³"
        PM2_5.text = list[position].PM2_5 + "㎍/m³"
        rain.text = list[position].rain + "%"
        temp.text = list[position].temp + "도"
        newsTitle.text = list[position].newsTitle
        //.을 기준으로 뉴스 내용을 나눠서 0번째 인덱스
        newsContent.text = list[position].newsContent.split(".")[0]
//-----------------------------------------------------------------------

        fun doDayOfWeek(): String? {
            val cal: Calendar = Calendar.getInstance()
            var strWeek: String? = null
            val nWeek: Int = cal.get(Calendar.DAY_OF_WEEK)

            if (nWeek == 1) {
                strWeek = "일"
            } else if (nWeek == 2) {
                strWeek = "월"
            } else if (nWeek == 3) {
                strWeek = "화"
            } else if (nWeek == 4) {
                strWeek = "수"
            } else if (nWeek == 5) {
                strWeek = "목"
            } else if (nWeek == 6) {
                strWeek = "금"
            } else if (nWeek == 7) {
                strWeek = "토"
            }
            return strWeek
        }



        val now = LocalDate.now()

        val strnow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        Log.d("오늘날짜", strnow)
        Log.d("오늘요일", doDayOfWeek().toString())
        Log.d("확인하기", list[position].dt.toString())
        dateNum.text = strnow + doDayOfWeek().toString() +"요일"

// ----------------------------------------------------------------------

        when (list[position].dt) {
            -1 -> dateText.text = "어제 날씨"
            0 -> dateText.text = "오늘 날씨"
            1 -> dateText.text = "내일 날씨"
        }


        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int = list.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

}
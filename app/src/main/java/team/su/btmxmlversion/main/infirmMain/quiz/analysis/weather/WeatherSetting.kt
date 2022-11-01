package team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather

import android.util.Log
import team.su.btmxmlversion.R
import team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather.data.TheWeatherByDay
import team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather.data.WeatherData
import team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather.data.WeatherIcon
import team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather.data.WeeklyForecast
import team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather.models.WeatherExamples
import team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather.models.WeatherModel
import java.lang.Math.random
import java.lang.StrictMath.random

class WeatherSetting {

    companion object {
        private val weatherData = WeatherData(
            weeklyForecastImages = listOf(
                WeeklyForecast(
                    weeklyForecastImage = R.drawable.weather_question_one,
                    theWeatherByDays = listOf(
                        TheWeatherByDay(R.drawable.fog_icon),
                        TheWeatherByDay(R.drawable.rain_icon),
                        TheWeatherByDay(R.drawable.rain_icon),
                        TheWeatherByDay(R.drawable.yellow_dust_icon),
                        TheWeatherByDay(R.drawable.yellow_dust_icon),
                        TheWeatherByDay(R.drawable.rain_icon),
                        TheWeatherByDay(R.drawable.fog_icon),
                    )
                ),
                WeeklyForecast(
                    weeklyForecastImage = R.drawable.weather_question_two,
                    theWeatherByDays = listOf(
                        TheWeatherByDay(R.drawable.fog_icon),
                        TheWeatherByDay(R.drawable.sunny_icon),
                        TheWeatherByDay(R.drawable.sunny_icon),
                        TheWeatherByDay(R.drawable.rain_icon),
                        TheWeatherByDay(R.drawable.fog_icon),
                        TheWeatherByDay(R.drawable.cloudy_small_icon),
                        TheWeatherByDay(R.drawable.sunny_icon),
                    )
                ),
                WeeklyForecast(
                    weeklyForecastImage = R.drawable.weather_question_three,
                    theWeatherByDays = listOf(
                        TheWeatherByDay(R.drawable.fog_icon),
                        TheWeatherByDay(R.drawable.fog_icon),
                        TheWeatherByDay(R.drawable.snow_icon),
                        TheWeatherByDay(R.drawable.snow_icon),
                        TheWeatherByDay(R.drawable.rain_icon),
                        TheWeatherByDay(R.drawable.rain_icon),
                        TheWeatherByDay(R.drawable.snow_icon),
                    )
                ),
            ),
            weatherIcons = listOf(
                WeatherIcon(R.drawable.cloudy_icon),
                WeatherIcon(R.drawable.cloudy_many_icon),
                WeatherIcon(R.drawable.cloudy_small_icon),
                WeatherIcon(R.drawable.fog_icon),
                WeatherIcon(R.drawable.rain_icon),
                WeatherIcon(R.drawable.shower_icon),
                WeatherIcon(R.drawable.snow_icon),
                WeatherIcon(R.drawable.strong_rain_icon),
                WeatherIcon(R.drawable.sunny_icon),
                WeatherIcon(R.drawable.yellow_dust_icon),
            )
        )
    }

    private var randomIndex = 0

    internal fun setWeather(): WeatherModel {
        val day = hashMapOf(0 to "일", 1 to "월", 2 to "화", 3 to "수", 4 to "목", 5 to "금", 6 to "토")
            .run {
                val randomDay = values.random()
                entries.forEach {
                    if (it.value == randomDay) {
                        randomIndex = it.key
                    }
                }
                randomDay
            }


        val weeklyForecast = weatherData.weeklyForecastImages.random()

        val question = StringBuilder()
            .append("Q. ")
            .append(day)
            .append("요일의 날씨는?").toString()

        val questionImage = weeklyForecast.weeklyForecastImage

        val answerImage = weeklyForecast.theWeatherByDays[randomIndex].weather

        val exampleImages = arrayListOf<WeatherExamples>().apply {
            mutableSetOf<Int>()
                .apply {
                    add(weatherData.weatherIcons.indexOf(WeatherIcon(answerImage)))
                    while (size < 4) {
                        add((0 until weatherData.weatherIcons.size - 1).random())
                    }
                }
                .shuffled()
                .forEach { index ->
                    this.add(WeatherExamples(weatherData.weatherIcons[index].icon))
                }
        }

        return WeatherModel(
            question = question,
            questionImage = questionImage,
            answerImage = answerImage,
            exampleImages = exampleImages,
        )
    }


}
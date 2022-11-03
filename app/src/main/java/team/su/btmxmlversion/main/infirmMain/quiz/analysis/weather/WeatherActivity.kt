package team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather

import android.os.Bundle
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMultipleChoiceQuizBinding
import team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather.adapter.WeatherExampleRvAdapter

class WeatherActivity: BaseActivity<ActivityMultipleChoiceQuizBinding>(ActivityMultipleChoiceQuizBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherModel = WeatherSetting().setWeather()

        binding.question.text = weatherModel.question
        setTimer(5, binding.timerCount, binding.root.context)
        binding.questionImage.layoutParams.width = 900
        binding.questionImage.layoutParams.height = 300
        binding.questionImage.setImageResource(weatherModel.questionImage)
        binding.answerRv.adapter =
            WeatherExampleRvAdapter(
                exampleImages = weatherModel.exampleImages,
                answerImage = weatherModel.answerImage,
                timeOut = { timerTask?.cancel() }
            )
    }

}
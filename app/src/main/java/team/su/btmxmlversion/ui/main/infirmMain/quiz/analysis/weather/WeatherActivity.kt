package team.su.btmxmlversion.ui.main.infirmMain.quiz.analysis.weather

import android.os.Bundle
import team.su.btmxmlversion.base.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMultipleChoiceQuizBinding
import team.su.btmxmlversion.ui.main.infirmMain.quiz.analysis.weather.adapter.WeatherExampleRvAdapter
import team.su.btmxmlversion.ui.main.infirmMain.quiz.until.QuizType

class WeatherActivity: BaseActivity<ActivityMultipleChoiceQuizBinding>(ActivityMultipleChoiceQuizBinding::inflate) {

    companion object {
        const val TIMER_COUNT = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isDiagnosis = intent.getBooleanExtra("isDiagnosis", false)
        val weatherModel = WeatherSetting().setWeather()

        binding.question.text = weatherModel.question
        setTimer(
            count = TIMER_COUNT,
            textView = binding.timerCount,
            context = this
        )
        binding.questionImage.apply {
            layoutParams.width = 900
            layoutParams.height = 300
            setImageResource(weatherModel.questionImage)
        }
        binding.answerRv.adapter =
            WeatherExampleRvAdapter(
                exampleImages = weatherModel.exampleImages,
                answerImage = weatherModel.answerImage,
                timeOut = { timerTask?.cancel() },
                onPassChanged = {
                    onQuizResult(
                        onPass = it,
                        quizType = QuizType.ANALYSIS_VALUE,
                        count = TIMER_COUNT
                    )
                },
                isDiagnosis = isDiagnosis
            )
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}
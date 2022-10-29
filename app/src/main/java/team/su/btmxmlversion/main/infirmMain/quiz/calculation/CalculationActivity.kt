package team.su.btmxmlversion.main.infirmMain.quiz.calculation

import android.content.Context
import android.os.Bundle
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMultipleChoiceQuizBinding
import team.su.btmxmlversion.main.infirmMain.quiz.calculation.service.CalculationService
import java.util.Timer
import java.util.TimerTask

class CalculationActivity:
    BaseActivity<ActivityMultipleChoiceQuizBinding>(ActivityMultipleChoiceQuizBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val calculationService = CalculationService()

        binding.question.text = "Q. 다음 식을 계산하시오."
        binding.timerCount.text = setTimer()
        binding.questionText.text = calculationService.setQuestionText()


    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun setTimer(): String {
        var time = 6

        timerTask = kotlin.concurrent.timer(period = 1000) {
            time--

            if (time == 0) {
                timerTask?.cancel()
                //(context as CalculationActivity).finish()
            }
        }

    }

}
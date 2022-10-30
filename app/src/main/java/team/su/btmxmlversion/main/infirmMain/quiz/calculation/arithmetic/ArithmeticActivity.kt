package team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic

import android.os.Bundle
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMultipleChoiceQuizBinding
import team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic.adapter.ArithmeticExampleRvAdapter
import team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic.service.ArithmeticService

class ArithmeticActivity:
    BaseActivity<ActivityMultipleChoiceQuizBinding>(ActivityMultipleChoiceQuizBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val calculationService = ArithmeticService()

        binding.question.text = "Q. 다음 식을 계산하시오."
        binding.questionText.text = calculationService.setQuestionText()
        setTimer(5, binding.timerCount, binding.root.context)

        val setExample = calculationService.setExampleText()

        binding.answerRv.adapter =
            ArithmeticExampleRvAdapter(setExample.first, setExample.second)

    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}
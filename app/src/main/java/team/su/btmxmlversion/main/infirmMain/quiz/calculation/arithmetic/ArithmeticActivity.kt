package team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic

import android.os.Bundle
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMultipleChoiceQuizBinding
import team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic.adapter.ArithmeticExampleRvAdapter

class ArithmeticActivity :
    BaseActivity<ActivityMultipleChoiceQuizBinding>(ActivityMultipleChoiceQuizBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arithmeticModel = ArithmeticSetting().setArithmetic()

        binding.question.text = "Q. 다음 식을 계산하시오."
        binding.questionText.text = arithmeticModel.question
        setTimer(5, binding.timerCount, binding.root.context)
        binding.answerRv.adapter =
            ArithmeticExampleRvAdapter(
                examples = arithmeticModel.arithmeticExampleModels,
                answer = arithmeticModel.answer,
                onTimeout = {
                    timerTask?.cancel()
                }
            )

    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}
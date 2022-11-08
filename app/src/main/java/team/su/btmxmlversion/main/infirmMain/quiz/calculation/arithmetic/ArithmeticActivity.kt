package team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic

import android.os.Bundle
import android.util.Log
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMultipleChoiceQuizBinding
import team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic.adapter.ArithmeticExampleRvAdapter
import team.su.btmxmlversion.main.infirmMain.quiz.until.QuizType

class ArithmeticActivity :
    BaseActivity<ActivityMultipleChoiceQuizBinding>(ActivityMultipleChoiceQuizBinding::inflate) {

    companion object {
        const val TIMER_COUNT = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isDiagnosis = intent.getBooleanExtra("isDiagnosis", false)
        val arithmeticModel = ArithmeticSetting().setArithmetic()

        binding.question.text = "Q. 다음 식을 계산하시오."
        binding.questionText.text = arithmeticModel.question
        setTimer(
            count = TIMER_COUNT,
            textView = binding.timerCount,
            context = this
        )
        binding.answerRv.adapter =
            ArithmeticExampleRvAdapter(
                examples = arithmeticModel.arithmeticExampleModels,
                answer = arithmeticModel.answer,
                onTimeout = { timerTask?.cancel() },
                onPassChanged = {
                    onQuizResult(
                        onPass = it,
                        quizType = QuizType.CALCULATION_VALUE,
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
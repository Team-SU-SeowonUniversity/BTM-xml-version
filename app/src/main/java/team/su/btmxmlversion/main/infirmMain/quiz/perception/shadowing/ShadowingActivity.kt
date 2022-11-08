package team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing

import android.os.Bundle
import android.util.Log
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMultipleChoiceQuizBinding
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.adapter.AnswerRvAdapter
import team.su.btmxmlversion.main.infirmMain.quiz.until.QuizType

class ShadowingActivity :
    BaseActivity<ActivityMultipleChoiceQuizBinding>(ActivityMultipleChoiceQuizBinding::inflate) {

    companion object {
        const val TIMER_COUNT = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isDiagnosis = intent.getBooleanExtra("isDiagnosis", false)
        val shadowingModel = ShadowingSetting().setShadowing()

        binding.question.text = "Q. 다음 중 그림에 맞는 사진을 고르시오."
        setTimer(
            count = TIMER_COUNT,
            textView = binding.timerCount,
            context = this
        )
        binding.questionImage.setImageResource(shadowingModel.questionImage)
        binding.answerRv.adapter =
            AnswerRvAdapter(
                examples = shadowingModel.examples,
                answer = shadowingModel.answer,
                timeOut = { timerTask?.cancel() },
                isDiagnosis = isDiagnosis,
                onPassChanged = {
                    onQuizResult(
                        onPass = it,
                        quizType = QuizType.PERCEPTION_VALUE,
                        count = TIMER_COUNT
                    )
                }
            )

    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}

package team.su.btmxmlversion.ui.main.infirmMain.quiz.perception.blinking

import android.os.Bundle
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.su.btmxmlversion.base.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMultipleChoiceQuizBinding
import team.su.btmxmlversion.ui.main.infirmMain.quiz.perception.blinking.adapter.BlinkingAnswerRvAdapter
import team.su.btmxmlversion.ui.main.infirmMain.quiz.until.QuizType

class BlinkingActivity: BaseActivity<ActivityMultipleChoiceQuizBinding>(ActivityMultipleChoiceQuizBinding::inflate) {

    companion object {
        const val TIMER_COUNT = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isDiagnosis = intent.getBooleanExtra("isDiagnosis", false)
        val blinkingModel = BlinkingSetting().setBlinking()

        binding.question.text = "Q. 깜박이는 과일은 무엇일까요?"
        binding.questionImage.run {
            setImageResource(blinkingModel.questionImage)
            layoutParams.height = 600
            layoutParams.width = 600
            CoroutineScope(Dispatchers.Main).launch {
                repeat(5) {
                    this@run.visibility = View.VISIBLE
                    delay(1000)
                    this@run.visibility = View.INVISIBLE
                    delay(1000)
                }
            }
        }
        setTimer(
            count = TIMER_COUNT,
            textView = binding.timerCount,
            context = this
        )
        binding.answerRv.adapter =
            BlinkingAnswerRvAdapter(
                examples = blinkingModel.examples,
                answer = blinkingModel.answer,
                timeOut = { timerTask?.cancel() },
                onPassChanged = {
                    onQuizResult(
                        onPass = it,
                        quizType = QuizType.PERCEPTION_VALUE,
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
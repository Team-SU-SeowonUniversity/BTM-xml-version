package team.su.btmxmlversion.main.infirmMain.quiz.memory.blinking

import android.os.Bundle
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMultipleChoiceQuizBinding
import team.su.btmxmlversion.main.infirmMain.quiz.memory.blinking.adapter.BlinkingAnswerRvAdapter

class BlinkingActivity: BaseActivity<ActivityMultipleChoiceQuizBinding>(ActivityMultipleChoiceQuizBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        setTimer(10, binding.timerCount, this)
        binding.answerRv.adapter =
            BlinkingAnswerRvAdapter(
                examples = blinkingModel.examples,
                answer = blinkingModel.answer,
                timeOut = { timerTask?.cancel() }
            )
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}
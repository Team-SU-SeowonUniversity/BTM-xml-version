package team.su.btmxmlversion.main.infirmMain.quiz.memory.blinking

import android.os.Bundle
import android.view.animation.AnimationUtils
import team.su.btmxmlversion.R
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMultipleChoiceQuizBinding
import team.su.btmxmlversion.main.infirmMain.quiz.memory.blinking.adapter.BlinkingAnswerRvAdapter

class BlinkingActivity: BaseActivity<ActivityMultipleChoiceQuizBinding>(ActivityMultipleChoiceQuizBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val blinkingModel = BlinkingSetting().setBlinking()
        val anim = AnimationUtils.loadAnimation(this, R.anim.blink_animation)

        binding.question.text = "Q. 깜박이는 과일은 무엇일까요?"
        binding.questionImage.apply {
            setImageResource(blinkingModel.questionImage)
            layoutParams.height = 600
            layoutParams.width = 600
            startAnimation(anim)
        }
        setTimer(5, binding.timerCount, this)
        binding.answerRv.adapter =
            BlinkingAnswerRvAdapter(
                examples = blinkingModel.examples,
                answer = blinkingModel.answer,
                timeOut = { timerTask?.cancel() }
            )

    }

}
package team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing

import android.os.Bundle
import android.util.Log
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMultipleChoiceQuizBinding
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.adapter.AnswerRvAdapter

class ShadowingActivity:
    BaseActivity<ActivityMultipleChoiceQuizBinding>(ActivityMultipleChoiceQuizBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shadowingModel = ShadowingSetting().setShadowing()

        binding.question.text = "Q. 다음 중 그림에 맞는 사진을 고르시오."
        setTimer(10, binding.timerCount, binding.root.context)
        binding.questionImage.setImageResource(shadowingModel.questionImage)
        binding.answerRv.adapter =
            AnswerRvAdapter(
                exampleImages = shadowingModel.shadowingExampleModels,
                answerImage = shadowingModel.answerImage,
                timeOut = { timerTask?.cancel() }
            )

    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}

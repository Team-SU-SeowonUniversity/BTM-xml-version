package team.su.btmxmlversion.main.infirmMain.quiz.memory.HwatuCard

import android.os.Bundle
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMultipleChoiceQuizBinding
import team.su.btmxmlversion.main.infirmMain.quiz.memory.HwatuCard.adapter.HwatuCardAnswerRvAdapter
import team.su.btmxmlversion.main.infirmMain.quiz.until.QuizType

class HwatuCardActivity: BaseActivity<ActivityMultipleChoiceQuizBinding>(ActivityMultipleChoiceQuizBinding::inflate) {

    companion object {
        const val TIMER_COUNT = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isDiagnosis = intent.getBooleanExtra("isDiagnosis", false)
        val hwatuCardModel = HwatuCardSetting().setHwatuCard()

        binding.question.text = "Q. 다음 사진을 집중하여 봐주세요!"
        binding.questionImage.apply {
            layoutParams.height = 700
            layoutParams.width = 400
            setImageResource(hwatuCardModel.questionImage)
        }

        binding.answerRv.visibility = View.INVISIBLE
        binding.timerCount.visibility = View.INVISIBLE
        binding.timerImage.visibility = View.INVISIBLE
        binding.questionImageBottomDivider.visibility = View.GONE

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            binding.answerRv.visibility = View.VISIBLE
            binding.timerCount.visibility = View.VISIBLE
            binding.timerImage.visibility = View.VISIBLE

            binding.question.text = "Q. 방금 나온 사진은 무엇인가요?"
            binding.questionImage.apply {
                layoutParams.height = 300
                layoutParams.width = 300
                setImageResource(R.drawable.question_mark)
            }
            setTimer(
                count = TIMER_COUNT,
                textView = binding.timerCount,
                context = this@HwatuCardActivity
            )
            binding.answerRv.adapter =
                HwatuCardAnswerRvAdapter(
                    examples = hwatuCardModel.examples,
                    answer = hwatuCardModel.answer,
                    timeOut = { timerTask?.cancel() },
                    onPassChanged = {
                        onQuizResult(
                            onPass = it,
                            quizType = QuizType.MEMORY_VALUE,
                            count = TIMER_COUNT
                        )
                    },
                    isDiagnosis = isDiagnosis
                )
        }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}
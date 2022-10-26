package team.su.btmxmlversion.main.infirmMain.multipleChoiceQuiz

import android.os.Bundle
import team.su.btmxmlversion.R
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMultipleChoiceQuizBinding
import team.su.btmxmlversion.main.infirmMain.multipleChoiceQuiz.adapter.AnswerRvAdapter
import team.su.btmxmlversion.main.infirmMain.multipleChoiceQuiz.models.ExampleImage
import team.su.btmxmlversion.main.infirmMain.multipleChoiceQuiz.models.ExampleImageModel
import team.su.btmxmlversion.main.infirmMain.multipleChoiceQuiz.models.MultipleChoiceData
import team.su.btmxmlversion.main.infirmMain.multipleChoiceQuiz.models.QuestionImage

class MultipleChoiceQuizActivity:BaseActivity<ActivityMultipleChoiceQuizBinding>(ActivityMultipleChoiceQuizBinding::inflate) {

    private var randomIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val item = multipleChoiceAddData()

        binding.questionText.text = item.question
        setTimer(item.count)
        setRandomQuestionImage(item)
        setRandomExampleImage(item)

    }

    private fun multipleChoiceAddData(): MultipleChoiceData {
        return MultipleChoiceData(
            question = "Q. 다음 중 그림에 맞는 사진을 고르시오.",
            count = 10,
            questionImage = listOf(
                QuestionImage(R.drawable.fox_shadow),
                QuestionImage(R.drawable.bear_shadow),
                QuestionImage(R.drawable.leopard_shadow),
                QuestionImage(R.drawable.lion_shadow),
                QuestionImage(R.drawable.mouse_shadow),
                QuestionImage(R.drawable.panda_shadow),
                QuestionImage(R.drawable.pig_shadow),
                QuestionImage(R.drawable.rabbit_shadow),
            ),
            example = listOf(
                ExampleImage(R.drawable.fox),
                ExampleImage(R.drawable.bear),
                ExampleImage(R.drawable.leopard),
                ExampleImage(R.drawable.lion),
                ExampleImage(R.drawable.mouse),
                ExampleImage(R.drawable.panda),
                ExampleImage(R.drawable.pig),
                ExampleImage(R.drawable.rabbit),
            )
        )
    }

    /*
        1. 정답 인덱스 추출
        2. 4개의 인덱스를 저장하는 set 리스트에 정답 번호 추가
        3. 0 ~ (예시 이미지 리스트 사이즈 - 1) 중 set 리스트에 중복되지 않는 수 3개를 추가로 뽑기
            4-1. 뽑은 수를 예시 인덱스에 리스트에 추가
        5. 셔플
        6. data class인 ExampleImageModel에 리스트들을 추가
        7. 어댑터 연결
     */
    private fun setRandomExampleImage(item: MultipleChoiceData) {
        val answerNum = randomIndex
        val exampleList = arrayListOf<ExampleImageModel>()
        val exampleIndexList = mutableSetOf<Int>().apply {
            add(answerNum)
            while (size < 4) {
                add((0 until item.example.size - 1).random())
            }
        }.shuffled()

        exampleIndexList.forEach { index ->
            exampleList.add(ExampleImageModel(item.example[index].exampleImage))
        }

        binding.answerRv.adapter = AnswerRvAdapter(exampleList, item.example[answerNum].exampleImage)

    }

    private fun setRandomQuestionImage(item: MultipleChoiceData) {
        val totalImageList = item.questionImage.size - 1
        randomIndex = (0..totalImageList).random()

        binding.questionImage.setImageResource(item.questionImage[randomIndex].questionImage)
    }

    private fun setTimer(count: Int) {
        var time = count + 1

        timerTask = kotlin.concurrent.timer(period = 1000) {
            time--

            if (time == 0) {
                timerTask?.cancel()
                finish()
            }

            runOnUiThread {
                binding.timerCount.text = time.toString()
            }
        }
    }

}

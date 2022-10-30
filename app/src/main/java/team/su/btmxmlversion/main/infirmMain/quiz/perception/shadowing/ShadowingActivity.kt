package team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing

import android.os.Bundle
import team.su.btmxmlversion.R
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMultipleChoiceQuizBinding
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.adapter.AnswerRvAdapter
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.data.ShadowImage
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.models.ExampleImageModel
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.data.ShadowingData
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.data.ShadowExample
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.repository.ShadowingRepository

class ShadowingActivity:
    BaseActivity<ActivityMultipleChoiceQuizBinding>(ActivityMultipleChoiceQuizBinding::inflate), ShadowingRepository {

    private var randomIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val item = shadowingAddData()

        binding.question.text = item.question
        setTimer(item.count, binding.timerCount, binding.root.context)
        setRandomQuestionImage(item)
        setRandomExampleImage(item)

    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    override fun shadowingAddData(): ShadowingData {
        return ShadowingData(
            question = "Q. 다음 중 그림에 맞는 사진을 고르시오.",
            count = 10,
            questionImages = listOf(
                ShadowImage(R.drawable.fox_shadow),
                ShadowImage(R.drawable.bear_shadow),
                ShadowImage(R.drawable.leopard_shadow),
                ShadowImage(R.drawable.lion_shadow),
                ShadowImage(R.drawable.mouse_shadow),
                ShadowImage(R.drawable.panda_shadow),
                ShadowImage(R.drawable.pig_shadow),
                ShadowImage(R.drawable.rabbit_shadow),
            ),
            examples = listOf(
                ShadowExample(R.drawable.fox),
                ShadowExample(R.drawable.bear),
                ShadowExample(R.drawable.leopard),
                ShadowExample(R.drawable.lion),
                ShadowExample(R.drawable.mouse),
                ShadowExample(R.drawable.panda),
                ShadowExample(R.drawable.pig),
                ShadowExample(R.drawable.rabbit),
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
    override fun setRandomExampleImage(item: ShadowingData) {
        val answerNum = randomIndex
        val exampleList = arrayListOf<ExampleImageModel>()
        val exampleIndexList = mutableSetOf<Int>().apply {
            add(answerNum)
            while (size < 4) {
                add((0 until item.examples.size - 1).random())
            }
        }.shuffled()

        exampleIndexList.forEach { index ->
            exampleList.add(ExampleImageModel(item.examples[index].shadowExample))
        }

        binding.answerRv.adapter = AnswerRvAdapter(exampleList, item.examples[answerNum].shadowExample)

    }

    override fun setRandomQuestionImage(item: ShadowingData) {
        val totalImageList = item.questionImages.size - 1
        randomIndex = (0..totalImageList).random()

        binding.questionImage.setImageResource(item.questionImages[randomIndex].shadowImage)
    }

}

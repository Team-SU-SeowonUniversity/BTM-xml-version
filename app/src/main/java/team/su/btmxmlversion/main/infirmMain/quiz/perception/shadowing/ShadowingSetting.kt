package team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing

import team.su.btmxmlversion.R
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.data.ShadowExample
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.data.ShadowImage
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.data.ShadowingData
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.models.ExampleImages
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.models.ShadowingModel

internal class ShadowingSetting {

    companion object {
        private val shadowingData = ShadowingData(
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
            examplesImages = listOf(
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
    internal fun setShadowing(): ShadowingModel {
        val randomIndex = (0 until shadowingData.questionImages.size - 1).random()
        val exampleList = arrayListOf<ExampleImages>().apply {
            mutableSetOf<Int>()
                .apply {
                    add(randomIndex)
                    while (size < 4) {
                        add((0 until shadowingData.examplesImages.size - 1).random())
                    }
                }
                .shuffled()
                .forEach { index ->
                    this.add(ExampleImages(shadowingData.examplesImages[index].shadowExample))
                }
        }

        return ShadowingModel(
            questionImage = shadowingData.questionImages[randomIndex].shadowImage,
            shadowingExampleModels = exampleList,
            answerImage = shadowingData.examplesImages[randomIndex].shadowExample
        )
    }

}
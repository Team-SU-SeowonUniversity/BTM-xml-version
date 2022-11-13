package team.su.btmxmlversion.ui.main.infirmMain.quiz.intuition.shadowing

import team.su.btmxmlversion.R
import team.su.btmxmlversion.ui.main.infirmMain.quiz.intuition.shadowing.data.Shadowing
import team.su.btmxmlversion.ui.main.infirmMain.quiz.intuition.shadowing.data.ShadowingResponse
import team.su.btmxmlversion.ui.main.infirmMain.quiz.intuition.shadowing.models.ShadowingExamples
import team.su.btmxmlversion.ui.main.infirmMain.quiz.intuition.shadowing.models.ShadowingModel

internal class ShadowingSetting {

    companion object {
        private val shadowingData = ShadowingResponse(
            result = listOf(
                Shadowing(R.drawable.fox_shadow, R.drawable.fox),
                Shadowing(R.drawable.bear_shadow, R.drawable.bear),
                Shadowing(R.drawable.leopard_shadow, R.drawable.leopard),
                Shadowing(R.drawable.lion_shadow, R.drawable.lion),
                Shadowing(R.drawable.mouse_shadow, R.drawable.mouse),
                Shadowing(R.drawable.panda_shadow, R.drawable.panda),
                Shadowing(R.drawable.pig_shadow, R.drawable.pig),
                Shadowing(R.drawable.rabbit_shadow, R.drawable.rabbit)
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
        val questionImage = shadowingData.result.random()
        val shadowingExamples = arrayListOf<ShadowingExamples>().apply {
            mutableSetOf<Int>()
                .apply {
                    add(questionImage.shadowExample)
                    while (size < 4) {
                        add(shadowingData.result.random().shadowExample)
                    }
                }
                .shuffled()
                .forEach { index ->
                    add(ShadowingExamples(index))
                }
        }

        return ShadowingModel(
            questionImage = questionImage.shadowImage,
            examples = shadowingExamples,
            answer = questionImage.shadowExample
        )
    }

}
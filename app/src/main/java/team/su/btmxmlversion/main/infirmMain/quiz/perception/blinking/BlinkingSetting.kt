package team.su.btmxmlversion.main.infirmMain.quiz.perception.blinking

import team.su.btmxmlversion.R
import team.su.btmxmlversion.main.infirmMain.quiz.perception.blinking.data.Blinking
import team.su.btmxmlversion.main.infirmMain.quiz.perception.blinking.data.BlinkingResponse
import team.su.btmxmlversion.main.infirmMain.quiz.perception.blinking.models.BlinkingExamples
import team.su.btmxmlversion.main.infirmMain.quiz.perception.blinking.models.BlinkingModel

class BlinkingSetting {

    companion object {
        private val blinkingResponse = BlinkingResponse(
            result = listOf(
                Blinking(R.drawable.apple,"사과"),
                Blinking(R.drawable.banana,"바나나"),
                Blinking(R.drawable.kiwi,"키위"),
                Blinking(R.drawable.melon,"메론"),
                Blinking(R.drawable.orange,"오렌지"),
                Blinking(R.drawable.peach,"복숭아"),
                Blinking(R.drawable.pineapple,"파인애플"),
                Blinking(R.drawable.strawberry,"딸기"),
                Blinking(R.drawable.watermelon,"수박"),
            )
        )
    }

    internal fun setBlinking(): BlinkingModel {
        val randomChoice = blinkingResponse.result.random()
        val examples = arrayListOf<BlinkingExamples>()
            .apply {
                mutableSetOf<String>()
                    .apply {
                        add(randomChoice.answer)
                        while (size < 4) {
                            add(blinkingResponse.result.random().answer)
                        }
                    }
                    .shuffled()
                    .forEach { index ->
                        add(BlinkingExamples(index))
                    }
            }.toList()

        return BlinkingModel(
            questionImage = randomChoice.image,
            examples = examples,
            answer = randomChoice.answer
        )
    }

}
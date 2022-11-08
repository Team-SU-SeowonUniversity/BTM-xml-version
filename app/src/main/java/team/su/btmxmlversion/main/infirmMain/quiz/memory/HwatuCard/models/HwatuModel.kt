package team.su.btmxmlversion.main.infirmMain.quiz.memory.HwatuCard.models

import team.su.btmxmlversion.main.infirmMain.quiz.perception.blinking.models.BlinkingExamples

data class HwatuModel(
    val questionImage: Int,
    val examples: List<HwatuExample>,
    val answer: Int,
)

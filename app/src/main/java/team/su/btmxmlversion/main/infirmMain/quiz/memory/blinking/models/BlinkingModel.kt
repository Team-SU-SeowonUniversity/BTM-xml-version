package team.su.btmxmlversion.main.infirmMain.quiz.memory.blinking.models

data class BlinkingModel(
    val questionImage: Int,
    val examples: List<BlinkingExamples>,
    val answer: String,
)

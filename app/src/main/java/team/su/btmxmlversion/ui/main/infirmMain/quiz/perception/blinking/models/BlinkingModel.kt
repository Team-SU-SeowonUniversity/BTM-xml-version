package team.su.btmxmlversion.ui.main.infirmMain.quiz.perception.blinking.models

data class BlinkingModel(
    val questionImage: Int,
    val examples: List<BlinkingExamples>,
    val answer: String,
)

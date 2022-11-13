package team.su.btmxmlversion.ui.main.infirmMain.quiz.intuition.shadowing.models

data class ShadowingModel(
    val questionImage: Int,
    val examples: List<ShadowingExamples>,
    val answer: Int,
)

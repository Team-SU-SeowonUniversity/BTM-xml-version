package team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.models

data class ShadowingModel(
    val questionImage: Int,
    val examples: List<ShadowingExamples>,
    val answer: Int,
)

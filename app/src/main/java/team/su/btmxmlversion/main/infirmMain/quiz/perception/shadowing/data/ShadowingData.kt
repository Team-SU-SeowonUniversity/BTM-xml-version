package team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.data

data class ShadowingData(
    val question: String,
    val count: Int,
    val questionImages: List<ShadowImage>,
    val examples: List<ShadowExample>
)

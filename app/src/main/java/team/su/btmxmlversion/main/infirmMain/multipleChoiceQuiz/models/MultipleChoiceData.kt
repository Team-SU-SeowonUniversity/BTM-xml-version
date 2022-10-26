package team.su.btmxmlversion.main.infirmMain.multipleChoiceQuiz.models

data class MultipleChoiceData(
    val question: String,
    val count: Int,
    val questionImage: List<QuestionImage>,
    val example: List<ExampleImage>
)

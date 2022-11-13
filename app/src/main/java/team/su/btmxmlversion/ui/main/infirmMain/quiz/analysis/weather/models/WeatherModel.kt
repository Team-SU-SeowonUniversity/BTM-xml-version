package team.su.btmxmlversion.ui.main.infirmMain.quiz.analysis.weather.models

data class WeatherModel(
    val question: String,
    val questionImage: Int,
    val answerImage: Int,
    val exampleImages: List<WeatherExamples>
)

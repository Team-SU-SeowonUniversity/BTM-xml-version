package team.su.btmxmlversion.main.infirmMain.quiz.models

data class QuizData(
    val quizName: String,
    val quizThumbnailImage: Int, // 추후 Uri 형식으로 바뀔 시 String으로 바꾸고 Glide 라이브러리 추가 변경
    val quizType: Int,
    val quizId: Int,
)
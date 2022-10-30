package team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic.repository

import team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic.model.ArithmeticExampleModel

interface ArithmeticRepository {

    fun setQuestionText(): StringBuilder

    fun setExampleText(): Pair<List<ArithmeticExampleModel>, Int>

}
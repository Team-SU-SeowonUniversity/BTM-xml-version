package team.su.btmxmlversion.main.infirmMain.quiz.calculation.service

import team.su.btmxmlversion.main.infirmMain.quiz.calculation.repository.CalculationRepository

class CalculationService: CalculationRepository {

    companion object {
        const val ADD = 0
        const val MINUS = 1
        const val MULTIPLICATION = 2
        const val DIVISION = 3

        var ANSWER: Int = 0
    }

    override fun setQuestionText(): StringBuilder {
        val operators = listOf(0,1,2,3).random()
        var leftOperand = (0..9).random()
        var rightOperand = (0..9).random()
        val sb = StringBuilder()

        when(operators) {
            ADD -> {
                sb.append(leftOperand.toString())
                    .append(" + ")
                    .append(rightOperand.toString())

                ANSWER = leftOperand + rightOperand
            }
            MINUS -> {
                rightOperand = (0..leftOperand).random()

                sb.append(leftOperand.toString())
                    .append(" - ")
                    .append(rightOperand.toString())

                ANSWER = leftOperand - rightOperand
            }
            MULTIPLICATION -> {
                sb.append(leftOperand.toString())
                    .append(" X ")
                    .append(rightOperand.toString())

                ANSWER = leftOperand * rightOperand
            }
            DIVISION -> {
                if (leftOperand == 0) {
                    leftOperand = (1..9).random()
                }

                if (leftOperand % rightOperand != 0) {
                    while (leftOperand % rightOperand != 0) {
                        rightOperand = (1..leftOperand).random()
                    }
                }

                sb.append(leftOperand.toString())
                    .append(" ÷ ")
                    .append(rightOperand.toString())

                ANSWER = leftOperand / rightOperand
            }
        }

        return sb.append(" = ?")
    }



}
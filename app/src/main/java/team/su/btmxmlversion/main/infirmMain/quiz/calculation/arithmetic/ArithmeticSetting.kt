package team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic

import team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic.model.ArithmeticModel

internal class ArithmeticSetting {

    companion object {
        const val ADD = 0
        const val MINUS = 1
        const val MULTIPLICATION = 2
        const val DIVISION = 3

        var ANSWER: Int = 0
    }

    internal fun setArithmetic(): ArithmeticModel {
        val operators = listOf(0, 1, 2, 3).random()
        var leftOperand = (0..9).random()
        var rightOperand = (0..9).random()
        val sb = StringBuilder()

        when (operators) {
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

        val example = setExample()

        return ArithmeticModel(
            sb.append(" = ?").toString(),
            example.first,
            example.second,
        )
    }

    private fun setExample(): Pair<List<Int>, Int> {
        var min = ANSWER - 1
        var max = ANSWER + 2

        if (ANSWER == 0) {
            min = 0
            max = 3
        }

        val exampleList = mutableSetOf<Int>().apply {
            while (this.size < 4) {
                this.add(((min..max).random()))
            }
        }.toList()

        return Pair(exampleList, ANSWER)
    }

}

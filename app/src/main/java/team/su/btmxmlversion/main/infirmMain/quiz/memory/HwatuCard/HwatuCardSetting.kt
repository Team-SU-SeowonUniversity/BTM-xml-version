package team.su.btmxmlversion.main.infirmMain.quiz.memory.HwatuCard

import team.su.btmxmlversion.R
import team.su.btmxmlversion.main.infirmMain.quiz.memory.HwatuCard.data.HwatuCard
import team.su.btmxmlversion.main.infirmMain.quiz.memory.HwatuCard.data.HwatuCardResponse
import team.su.btmxmlversion.main.infirmMain.quiz.memory.HwatuCard.models.HwatuExample
import team.su.btmxmlversion.main.infirmMain.quiz.memory.HwatuCard.models.HwatuModel

class HwatuCardSetting {

    companion object {
        val hwatuCardData = HwatuCardResponse(
            listOf(
                HwatuCard(R.drawable.january_daylight),
                HwatuCard(R.drawable.january_hongdan),
                HwatuCard(R.drawable.january_one),
                HwatuCard(R.drawable.january_two),
                HwatuCard(R.drawable.february_godori),
                HwatuCard(R.drawable.february_hongdan),
                HwatuCard(R.drawable.february_one),
                HwatuCard(R.drawable.february_two),
                HwatuCard(R.drawable.may_chodan),
                HwatuCard(R.drawable.may_ten),
                HwatuCard(R.drawable.may_one),
                HwatuCard(R.drawable.may_two),
                HwatuCard(R.drawable.april_godori),
                HwatuCard(R.drawable.april_chodan),
                HwatuCard(R.drawable.april_one),
                HwatuCard(R.drawable.april_two),
            )
        )
    }

    internal fun setHwatuCard(): HwatuModel {
        val questionImage = hwatuCardData.result.random().hwatuCard
        val examples = arrayListOf<HwatuExample>()
            .apply {
                mutableSetOf<Int>()
                    .apply {
                        add(questionImage)
                        while (size < 4) {
                            add(hwatuCardData.result.random().hwatuCard)
                        }
                    }
                    .shuffled()
                    .forEach { index ->
                        add(HwatuExample(index))
                    }
            }

        return HwatuModel(
            questionImage = questionImage,
            examples = examples,
            answer = questionImage
        )
    }

}
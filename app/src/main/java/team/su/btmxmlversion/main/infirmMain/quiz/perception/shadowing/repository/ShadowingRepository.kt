package team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.repository

import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.data.ShadowingData

interface ShadowingRepository {

    fun shadowingAddData(): ShadowingData

    fun setRandomExampleImage(item: ShadowingData)

    fun setRandomQuestionImage(item: ShadowingData)

}

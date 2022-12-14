package team.su.btmxmlversion.ui.main.infirmMain.quiz

import android.os.Bundle
import android.view.View
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentQuizListViewPagerBinding
import team.su.btmxmlversion.ui.main.infirmMain.quiz.adapter.QuizRvAdapter
import team.su.btmxmlversion.ui.main.infirmMain.quiz.data.Quiz
import team.su.btmxmlversion.ui.main.infirmMain.quiz.data.QuizResponse

class QuizListFragment:
    BaseFragment<FragmentQuizListViewPagerBinding>(FragmentQuizListViewPagerBinding::bind, R.layout.fragment_quiz_list_view_pager) {

    companion object {
        val quizResponse = QuizResponse(
            listOf(
                Quiz("그림자 찾기", R.drawable.shadowing_thumbnail, 2, 1),
                Quiz("깜박깜박", R.drawable.blinking_thmbnail, 0, 5),
                Quiz("화투패 맞추기", R.drawable.hwatu_thumbnail, 1, 2),
                Quiz("사칙연산", R.drawable.calculation_thumbnail, 3, 3),
                Quiz("일기예보", R.drawable.weather_thumbnail, 4, 4),
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey("quiz_type_index") }?.apply {
            val quizType = getInt("quiz_type_index")
            val quizData = quizResponse.result.filter { it.quizType == quizType }

            binding.quizListRv.adapter = QuizRvAdapter(quizData)
        }

    }

}